package org.ubp.ent.backend.core.model.teacher.contact.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressType extends ContactDetailsType {

    public AddressType(@JsonProperty("name") String name) {
        super(name);
    }

}
