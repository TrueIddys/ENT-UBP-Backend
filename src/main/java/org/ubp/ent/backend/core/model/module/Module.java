package org.ubp.ent.backend.core.model.module;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.core.model.course.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */
public class Module {

    private final String name;
    private final List<Course> courses = new ArrayList<>();
    private Long id;

    @JsonCreator
    public Module(@JsonProperty("name") final String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }

        this.name = name;
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

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cannot add a null " + Course.class.getName() + " to a " + getClass().getName());
        }
        this.courses.add(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        if (this.getId() == null || ((Module) o).getId() == null) return false;

        return Objects.equal(id, module.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
