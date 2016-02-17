package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutsiderTeacherDomain other = (OutsiderTeacherDomain) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
