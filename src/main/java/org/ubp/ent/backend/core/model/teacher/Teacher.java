package org.ubp.ent.backend.core.model.teacher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.name.Name;

/**
 * Created by Anthony on 16/01/2016.
 */
public class Teacher {

    private Long id;
    private Name name;
    private Contact contact;
    private TeacherType type;

    @JsonCreator
    public Teacher(@JsonProperty("name") Name name, @JsonProperty("contact") Contact contact, @JsonProperty("type") TeacherType type) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }
        if (contact == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Contact.class.getName());
        }
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + TeacherType.class.getName());
        }
        this.name = name;
        this.contact = contact;
        this.type = type;
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

    public TeacherType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher other = (Teacher) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
