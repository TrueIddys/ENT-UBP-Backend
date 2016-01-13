package org.ubp.ent.backend.core.model.teacher;

import org.ubp.ent.backend.core.model.teacher.contact.Contact;

/**
 * Created by Anthony on 11/01/2016.
 */
public class Teacher {

    private Long id;
    private Name name;
    private Contact contact;

    public Teacher(Name name, Contact contact) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }
        if (contact == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Contact.class.getName());
        }
        this.name = name;
        this.contact = contact;
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

    public Contact getContact() {
        return contact;
    }

}
