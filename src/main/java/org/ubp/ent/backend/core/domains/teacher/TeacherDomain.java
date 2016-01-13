package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDetailsDomain;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;
import org.ubp.ent.backend.core.model.teacher.Name;
import org.ubp.ent.backend.core.model.teacher.Teacher;

import javax.persistence.*;

/**
 * Created by Anthony on 11/01/2016.
 */
@Entity
@Table(name = "teacher")
public class TeacherDomain implements ModelTransformable<Teacher> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private NameDetailsDomain name;
    @Embedded
    private AddressDetailsDomain address;

    public TeacherDomain() {
    }

    public TeacherDomain(Teacher teacher) {
        this.id = teacher.getId();
        this.name = new NameDetailsDomain(teacher.getName());
        this.address = new AddressDetailsDomain(teacher.getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameDetailsDomain getName() {
        return name;
    }

    public AddressDetailsDomain getAddress() {
        return address;
    }

    @Override
    public Teacher toModel() {
        Name modelName = name.toModel();
        AddressDetails modelAddress = address.toModel();
        Teacher teacher = new Teacher(modelName, modelAddress);
        teacher.setId(id);

        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDomain domain = (TeacherDomain) o;
        if (id == null || domain.getId() == null) return false;
        return Objects.equal(id, domain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
