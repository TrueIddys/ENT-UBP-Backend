package org.ubp.ent.backend.core.model.teacher;

import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anthony on 11/01/2016.
 */
public class Teacher {

    private Long id;
    private Name name;
    private Set<Address> addresses;

    public Teacher(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }
        this.name = name;
        addresses = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
}
