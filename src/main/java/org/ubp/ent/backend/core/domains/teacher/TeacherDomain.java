package org.ubp.ent.backend.core.domains.teacher;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDomain;
import org.ubp.ent.backend.core.domains.teacher.name.NameDomain;
import org.ubp.ent.backend.core.model.teacher.Teacher;

import javax.persistence.*;

/**
 * Created by Anthony on 16/01/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TeacherDomain<T extends Teacher> implements ModelTransformable<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Embedded
    private NameDomain name;

    @Embedded
    private ContactDomain contact;

    protected TeacherDomain() {
    }

    public TeacherDomain(T model) {
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

}
