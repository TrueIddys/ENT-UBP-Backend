package org.ubp.ent.backend.core.model.teacher.contact.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Phone {

    private Long id;
    private PhoneDetails details;

    @JsonCreator
    public Phone(@JsonProperty("details") PhoneDetails details) {
        if (details == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a phone");
        }
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneDetails getDetails() {
        return details;
    }

}
