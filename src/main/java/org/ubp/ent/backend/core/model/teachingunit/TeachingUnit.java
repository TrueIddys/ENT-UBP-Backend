package org.ubp.ent.backend.core.model.teachingunit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnit {

    private final String name;
    private Long id;

    @JsonCreator
    public TeachingUnit(@JsonProperty("name") final String name) {
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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        TeachingUnit teachingUnit = (TeachingUnit) other;
        if (this.id == null || teachingUnit.id == null) return false;
        return Objects.equal(id, teachingUnit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
