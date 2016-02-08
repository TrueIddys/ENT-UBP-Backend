package org.ubp.ent.backend.core.model.teacher.contact.email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailType extends ContactDetailsType {

    @JsonCreator
    public EmailType(@JsonProperty("name") String name) {
        super(name);
    }

}
