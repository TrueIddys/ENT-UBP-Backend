package org.ubp.ent.backend.core.domains.teacher;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDomain;
import org.ubp.ent.backend.core.domains.teacher.name.NameDomain;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;

import javax.persistence.*;

/**
 * Created by Anthony on 14/01/2016.
 */
@Entity
@Table(name = "teacher")
public class TeacherDomain implements ModelTransformable<UniversityTeacher> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NameDomain name;

    @Embedded
    private ContactDomain contact;

    protected TeacherDomain() {
    }

    public TeacherDomain(UniversityTeacher model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + UniversityTeacher.class.getName());
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
    public UniversityTeacher toModel() {
        UniversityTeacher model = new UniversityTeacher(name.toModel(), contact.toModel());
        model.setId(id);
        return model;
    }

}
