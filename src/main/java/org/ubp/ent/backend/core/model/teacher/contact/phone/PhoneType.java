package org.ubp.ent.backend.core.model.teacher.contact.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneType extends ContactDetailsType {

    @JsonCreator
    public PhoneType(@JsonProperty("name") String name) {
        super(name);
    }

}
