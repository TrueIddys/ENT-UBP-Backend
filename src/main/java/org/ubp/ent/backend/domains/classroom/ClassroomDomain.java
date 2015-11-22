package org.ubp.ent.backend.domains.classroom;

import com.google.common.base.Objects;
import org.ubp.ent.backend.domains.ModelTransformable;
import org.ubp.ent.backend.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.model.classroom.Classroom;
import org.ubp.ent.backend.model.classroom.RoomCapacity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anthony on 21/11/2015.
 */
@Entity
@Table(name = "classroom")
public class ClassroomDomain implements ModelTransformable<Classroom, Long> {

    @Id
    @GeneratedValue
    @Column(name = "CLASSROOM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "primaryKey.classroom", cascade = CascadeType.ALL)
    private Set<RoomEquipmentDomain> equipments = new HashSet<>();

    private int capacity;

    public ClassroomDomain(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot instantiate a " + getClass().getName() + " with a null " + Classroom.class.getName());
        }
        id = classroom.getId();
        name = classroom.getName();
        capacity = classroom.getRoomCapacity().getMaxCapacity();
        classroom.getEquipments().forEach(e -> equipments.add(new RoomEquipmentDomain(e, this)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<RoomEquipmentDomain> getEquipments() {
        return equipments;
    }

    public int getRoomCapacity() {
        return capacity;
    }

    @Override
    public Classroom toModel() {
        Classroom classroom = new Classroom(name, new RoomCapacity(capacity));
        classroom.setId(id);
        equipments.forEach(e -> classroom.addEquipment(e.toModel()));

        return classroom;
    }

}
