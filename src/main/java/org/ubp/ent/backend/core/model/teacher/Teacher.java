package org.ubp.ent.backend.core.model.teacher;

/**
 * Created by Anthony on 11/01/2016.
 */
public class Teacher {

    private Long id;
    private Name name;
    private Address address;

    public Teacher(Name name, Address address) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }
        if (address == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Address.class.getName());
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

    public Address getAddress() {
        return address;
    }

}
