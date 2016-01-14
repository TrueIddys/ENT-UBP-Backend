package org.ubp.ent.backend.core.domains.teacher.contact.email;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDetailsTypeDomain;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anthony on 14/01/2016.
 */
@Entity
@Table(name = "email_type")
public class EmailTypeDomain extends ContactDetailsTypeDomain implements ModelTransformable<EmailType> {

    protected EmailTypeDomain() {
    }

    public EmailTypeDomain(EmailType type) {
        super(type);
    }

    @Override
    public EmailType toModel() {
        EmailType model = new EmailType(getName());
        model.setId(getId());

        return model;
    }
}
