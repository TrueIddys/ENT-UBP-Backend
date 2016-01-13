package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Phone extends ContactDetailsWrapper<PhoneType> {

    private PhoneDetails phoneDetails;

    public Phone(PhoneType addressType, PhoneDetails phoneDetails) {
        super(addressType);
        if (phoneDetails == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a phone");
        }
        this.phoneDetails = phoneDetails;
    }

    public PhoneType getPhoneType() {
        return super.getType();
    }

    public PhoneDetails getPhoneDetails() {
        return phoneDetails;
    }

}
