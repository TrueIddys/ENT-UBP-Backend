package org.ubp.ent.backend.core.model.teacher.contact.address;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressWrapper extends ContactDetailsWrapper<AddressType> {

    private Address address;

    public AddressWrapper(AddressType addressType, Address address) {
        super(addressType);
        if (address == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an address");
        }
        this.address = address;
    }

    public AddressType getAddressType() {
        return super.getType();
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressWrapper that = (AddressWrapper) o;
        return super.equals(o) &&
                Objects.equal(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), address);
    }

}
