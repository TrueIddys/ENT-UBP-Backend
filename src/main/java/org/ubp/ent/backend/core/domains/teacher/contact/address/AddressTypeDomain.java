package org.ubp.ent.backend.core.domains.teacher.contact.address;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.teacher.contact.ContactDetailsTypeDomain;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Anthony on 13/01/2016.
 */
@Entity
@Table(name = "address_type")
public class AddressTypeDomain extends ContactDetailsTypeDomain implements ModelTransformable<AddressType> {

    protected AddressTypeDomain() {
    }

    public AddressTypeDomain(AddressType type) {
        super(type);
    }

    @Override
    public AddressType toModel() {
        AddressType model = new AddressType(getName());
        model.setId(getId());

        return model;
    }

}
