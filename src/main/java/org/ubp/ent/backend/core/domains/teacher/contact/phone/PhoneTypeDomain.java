package org.ubp.ent.backend.core.domains.teacher.contact.phone;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDetailsTypeDomain;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anthony on 14/01/2016.
 */
@Entity
@Table(name = "phone_type")
public class PhoneTypeDomain extends ContactDetailsTypeDomain implements ModelTransformable<PhoneType> {

    public PhoneTypeDomain() {
    }

    public PhoneTypeDomain(PhoneType type) {
        super(type);
    }

    @Override
    public PhoneType toModel() {
        PhoneType model = new PhoneType(getName());
        model.setId(getId());

        return model;
    }

}
