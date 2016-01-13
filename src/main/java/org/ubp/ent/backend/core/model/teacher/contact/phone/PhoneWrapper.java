package org.ubp.ent.backend.core.model.teacher.contact.phone;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneWrapper extends ContactDetailsWrapper<PhoneType> {

    private Phone phone;

    public PhoneWrapper(PhoneType addressType, Phone phone) {
        super(addressType);
        if (phone == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a phone");
        }
        this.phone = phone;
    }

    public PhoneType getPhoneType() {
        return super.getType();
    }

    public Phone getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneWrapper that = (PhoneWrapper) o;
        return super.equals(o) &&
                Objects.equal(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), phone);
    }

}
