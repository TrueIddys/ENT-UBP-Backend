package org.ubp.ent.backend.core.domains.teacher;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.teacher.contact.Address;

import javax.persistence.Embeddable;

/**
 * Created by Anthony on 11/01/2016.
 */
@Embeddable
public class AddressDomain implements ModelTransformable<Address> {

    private String streetNumber;
    private String street;
    private String zip;
    private String city;

    public AddressDomain() {
    }

    public AddressDomain(Address address) {
        this.streetNumber = address.getStreetNumber();
        this.street = address.getStreet();
        this.zip = address.getZip();
        this.city = address.getCity();
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    @Override
    public Address toModel() {
        return new Address(streetNumber, street, zip, city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDomain that = (AddressDomain) o;
        return Objects.equal(zip, that.zip) &&
                Objects.equal(city, that.city) &&
                Objects.equal(streetNumber, that.streetNumber) &&
                Objects.equal(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(zip, city, streetNumber, street);
    }

}
