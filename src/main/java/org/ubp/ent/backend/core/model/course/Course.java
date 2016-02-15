package org.ubp.ent.backend.core.model.course;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.core.model.type.ClassroomType;

/**
 * Created by Maxime on 15/02/2016.
 */
public class Course {

    private final String name;
    private final ClassroomType type;
    private long id;

    @JsonCreator
    public Course(@JsonProperty("name") final String name, @JsonProperty("type") final ClassroomType type)
    {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + ClassroomType.class.getName());
        }

        this.name = name;
        this.type = type;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public ClassroomType getType()
    {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equal(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
