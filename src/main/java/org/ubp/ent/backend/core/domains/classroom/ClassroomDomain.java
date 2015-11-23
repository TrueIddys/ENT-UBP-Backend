package org.ubp.ent.backend.core.domains.classroom;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.RoomCapacity;

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

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "primaryKey.classroom", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<RoomEquipmentDomain> equipments = new HashSet<>();

    private int capacity;

    public ClassroomDomain() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDomain that = (ClassroomDomain) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
