package org.ubp.ent.backend.core.model.teacher.contact.email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Email {

    private Long id;
    private EmailType type;
    private EmailDetails details;

    @JsonCreator
    public Email(@JsonProperty("type") EmailType type, @JsonProperty("details") EmailDetails details) {
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a type");
        }
        if (details == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an email");
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

    public EmailType getType() {
        return type;
    }

    public EmailDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email that = (Email) o;
        return Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

}
