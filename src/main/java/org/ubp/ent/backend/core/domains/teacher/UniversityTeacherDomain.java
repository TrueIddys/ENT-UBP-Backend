package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anthony on 14/01/2016.
 */
@Entity
@Table(name = "university_teacher")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityTeacherDomain other = (UniversityTeacherDomain) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
