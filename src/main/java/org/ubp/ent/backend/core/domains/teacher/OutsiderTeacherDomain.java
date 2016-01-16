package org.ubp.ent.backend.core.domains.teacher;

import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anthony on 16/01/2016.
 */
@Entity
@Table(name = "outsider_teacher")
public class OutsiderTeacherDomain extends TeacherDomain<OutsiderTeacher> {

    protected OutsiderTeacherDomain() {
    }

    public OutsiderTeacherDomain(OutsiderTeacher model) {
        super(model);
    }

    @Override
    public OutsiderTeacher toModel() {
        OutsiderTeacher model = new OutsiderTeacher(getName().toModel(), getContact().toModel());
        model.setId(getId());
        return model;
    }

}
