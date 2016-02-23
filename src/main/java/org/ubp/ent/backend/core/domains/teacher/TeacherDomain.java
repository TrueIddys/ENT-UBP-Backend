package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDomain;
import org.ubp.ent.backend.core.domains.teacher.name.NameDomain;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherType;

import javax.persistence.*;

/**
 * Created by Anthony on 16/01/2016.
 */
@Entity
@Table(name = "teacher")
public class TeacherDomain implements ModelTransformable<Teacher> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NameDomain name;

    @Embedded
    private ContactDomain contact;

    private TeacherType type;

    protected TeacherDomain() {
    }

    public TeacherDomain(Teacher model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Teacher.class.getName());
        }
        id = model.getId();
        name = new NameDomain(model.getName());
        contact = new ContactDomain(model.getContact());
        this.type = model.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameDomain getName() {
        return name;
    }

    public ContactDomain getContact() {
        return contact;
    }

    public TeacherType getType() {
        return type;
    }

    @Override
    public Teacher toModel() {
        Teacher model = new Teacher(getName().toModel(), getContact().toModel(), getType());
        model.setId(this.getId());
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDomain other = (TeacherDomain) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
