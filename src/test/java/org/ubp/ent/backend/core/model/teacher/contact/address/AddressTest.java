package org.ubp.ent.backend.core.model.teacher.contact.address;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressTest {

    public static Address createOne() {
        return new Address(AddressTypeTest.createOne(), AddressDetailsTest.createOne());
    }

    public static Address createOne(String addressType) {
        return new Address(AddressTypeTest.createOne(addressType), AddressDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new Address(null, AddressDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new Address(AddressTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        AddressType type = AddressTypeTest.createOne();
        AddressDetails data = AddressDetailsTest.createOne();
        Address model = new Address(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getAddressType()).isEqualTo(type);
        assertThat(model.getAddressDetails()).isEqualTo(data);
    }

}