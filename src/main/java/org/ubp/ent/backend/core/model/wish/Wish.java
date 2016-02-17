package org.ubp.ent.backend.core.model.wish;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.teacher.Teacher;

/**
 * Created by Anthony on 17/02/2016.
 */
public class Wish {

    private Course course;
    private Teacher teacher;

    public Wish(@JsonProperty("course") final Course course, @JsonProperty("teacher") Teacher teacher) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + Course.class.getName());
        }
        if (teacher == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + Teacher.class.getName());
        }

        this.course = course;
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish other = (Wish) o;
        return Objects.equal(course, other.course) &&
                Objects.equal(teacher, other.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(course, teacher);
    }

}
