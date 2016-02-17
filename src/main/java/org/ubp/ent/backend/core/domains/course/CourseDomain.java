package org.ubp.ent.backend.core.domains.course;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import javax.persistence.*;

/**
 * Created by Maxime on 15/02/2016.
 */

@Entity
@Table(name = "course")
public class CourseDomain implements ModelTransformable<Course> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private ClassroomType type;

    @SuppressWarnings("unused")
    protected CourseDomain() {
    }

    public CourseDomain(Course model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Course.class.getName());
        }
        id = model.getId();
        name = model.getName();
        type = model.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ClassroomType getType() {
        return type;
    }

    @Override
    public Course toModel() {
        Course course = new Course(name, type);
        course.setId(id);

        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDomain other = (CourseDomain) o;
        if (this.id == null || other.id == null) return false;
        return Objects.equal(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
