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
public class UniversityTeacherDomain extends TeacherDomain<UniversityTeacher> {

    protected UniversityTeacherDomain() {
    }

    public UniversityTeacherDomain(UniversityTeacher model) {
        super(model);
    }

    @Override
    public UniversityTeacher toModel() {
        UniversityTeacher model = new UniversityTeacher(getName().toModel(), getContact().toModel());
        model.setId(this.getId());
        return model;
    }

}
