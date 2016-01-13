package org.ubp.ent.backend.core.model.teacher.contact.email;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsWrapper;

/**
 * Created by Anthony on 13/01/2016.
 */
public class Email extends ContactDetailsWrapper<EmailType> {

    private EmailDetails emailDetails;

    public Email(EmailType addressType, EmailDetails emailDetails) {
        super(addressType);
        if (emailDetails == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an email");
        }
        this.emailDetails = emailDetails;
    }

    public EmailType getEmailType() {
        return super.getType();
    }

    public EmailDetails getEmailDetails() {
        return emailDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email that = (Email) o;
        return super.equals(o) &&
                Objects.equal(emailDetails, that.emailDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), emailDetails);
    }

}
