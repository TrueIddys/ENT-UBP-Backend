package org.ubp.ent.backend.core.domains.wish;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Anthony on 17/02/2016.
 */
@Embeddable
public class WishDomainId implements Serializable {
    @ManyToOne
    private CourseDomain course;

    @ManyToOne
    private TeacherDomain teacher;


    protected WishDomainId() {
    }

    public WishDomainId(CourseDomain course, TeacherDomain teacher) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + CourseDomain.class.getName());
        }
        if (teacher == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + TeacherDomain.class.getName());
        }
        this.course = course;
        this.teacher = teacher;
    }

    public CourseDomain getCourse() {
        return course;
    }

    public TeacherDomain getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishDomainId that = (WishDomainId) o;
        return Objects.equal(course, that.course) &&
                Objects.equal(teacher, that.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(course, teacher);
    }
}
