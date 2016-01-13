package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.teacher.Name;

import javax.persistence.Embeddable;

/**
 * Created by Anthony on 11/01/2016.
 */
@Embeddable
public class NameDomain implements ModelTransformable<Name> {

    private String firstName;
    private String lastName;

    protected NameDomain() {

    }

    public NameDomain(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + Name.class.getName());
        }

        this.firstName = name.getFirstName();
        this.lastName = name.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public Name toModel() {
        return new Name(firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameDomain that = (NameDomain) o;
        return Objects.equal(firstName, that.firstName) &&
                Objects.equal(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName);
    }
}
