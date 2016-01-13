package org.ubp.ent.backend.core.model.teacher.contact.address;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Address extends ContactDetailsWrapper<AddressType> {

    private AddressDetails addressDetails;

    public Address(AddressType addressType, AddressDetails addressDetails) {
        super(addressType);
        if (addressDetails == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an address");
        }
        this.addressDetails = addressDetails;
    }

    public AddressType getAddressType() {
        return super.getType();
    }

    public AddressDetails getAddressDetails() {
        return addressDetails;
    }

}
