package org.ubp.ent.backend.core.model.teacher.contact.address;

import com.google.common.base.Objects;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Address {

    private Long id;
    private AddressType type;
    private AddressDetails details;

    public Address(AddressType type, AddressDetails details) {
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a type");
        }
        if (details == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an address");
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

    public AddressType getType() {
        return type;
    }

    public AddressDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

}
