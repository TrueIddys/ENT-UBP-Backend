package org.ubp.ent.backend.core.model.teacher;

import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;

/**
 * Created by Anthony on 11/01/2016.
 */
public class Teacher {

    private Long id;
    private Name name;
    private AddressDetails address;

    public Teacher(Name name, AddressDetails address) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }
        if (address == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + AddressDetails.class.getName());
        }

        this.name = name;
        this.address = address;
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

    public AddressDetails getAddress() {
        return address;
    }

}
