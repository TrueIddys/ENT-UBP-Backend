package org.ubp.ent.backend.core.domains.classroom;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.RoomCapacity;
import org.ubp.ent.backend.core.model.type.ClassroomType;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASSROOM_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "primaryKey.classroom", cascade = CascadeType.REMOVE)
    private Set<RoomEquipmentDomain> equipments = new HashSet<>();

    private int capacity;

    @ElementCollection(targetClass=ClassroomType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="classroom_types")
    private Set<ClassroomType> types;

    @SuppressWarnings("unused")
    public ClassroomDomain() {
    }

    public ClassroomDomain(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot instantiate a " + getClass().getName() + " with a null " + Classroom.class.getName());
        }
        id = classroom.getId();
        name = classroom.getName();
        capacity = classroom.getRoomCapacity().getMaxCapacity();
        types = Sets.newHashSet(classroom.getTypes());
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

    public Set<ClassroomType> getTypes() {
        return types;
    }

    @Override
    public Classroom toModel() {
        Classroom classroom = new Classroom(name, new RoomCapacity(capacity), types);
        classroom.setId(id);

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
