package org.ubp.ent.backend.core.model.teacher.contact.email;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailWrapper extends ContactDetailsWrapper<EmailType> {

    private Email email;

    public EmailWrapper(EmailType addressType, Email email) {
        super(addressType);
        if (email == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an email");
        }
        this.email = email;
    }

    public EmailType getEmailType() {
        return super.getType();
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailWrapper that = (EmailWrapper) o;
        return super.equals(o) &&
                Objects.equal(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), email);
    }

}
