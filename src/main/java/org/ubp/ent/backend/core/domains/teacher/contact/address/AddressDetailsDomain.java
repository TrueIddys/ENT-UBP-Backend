package org.ubp.ent.backend.core.domains.teacher.contact.address;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;

import javax.persistence.Embeddable;

/**
 * Created by Anthony on 11/01/2016.
 */
@Embeddable
public class AddressDetailsDomain implements ModelTransformable<AddressDetails> {

    private String streetNumber;
    private String street;
    private String zip;
    private String city;

    public AddressDetailsDomain() {
    }

    public AddressDetailsDomain(AddressDetails address) {
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
    public AddressDetails toModel() {
        return new AddressDetails(streetNumber, street, zip, city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDetailsDomain that = (AddressDetailsDomain) o;
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
