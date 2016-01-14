package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDomain;
import org.ubp.ent.backend.core.model.teacher.Teacher;

import javax.persistence.*;

/**
 * Created by Anthony on 14/01/2016.
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

    protected TeacherDomain() {
    }

    public TeacherDomain(Teacher model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Teacher.class.getName());
        }
        id = model.getId();
        name = new NameDomain(model.getName());
        contact = new ContactDomain(model.getContact());
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

    @Override
    public Teacher toModel() {
        Teacher model = new Teacher(name.toModel(), contact.toModel());
        model.setId(id);
        return model;
    }

}
