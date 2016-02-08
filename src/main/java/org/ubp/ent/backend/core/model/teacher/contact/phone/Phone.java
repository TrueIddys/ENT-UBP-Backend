package org.ubp.ent.backend.core.model.teacher.contact.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Phone {

    private Long id;
    private PhoneType type;
    private PhoneDetails details;

    @JsonCreator
    public Phone(@JsonProperty("type") PhoneType type, @JsonProperty("details") PhoneDetails details) {
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a type");
        }
        if (details == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a phone");
        }
        this.type = type;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public PhoneDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

}
