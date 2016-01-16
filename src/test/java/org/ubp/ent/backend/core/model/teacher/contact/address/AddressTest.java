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

    public static Address createOne(String addressTypeName) {
        return new Address(AddressTypeTest.createOne(addressTypeName), AddressDetailsTest.createOne());
    }

    public static Address createOne(AddressType addressType) {
        return new Address(addressType, AddressDetailsTest.createOne());
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
        assertThat(model.getType()).isEqualTo(type);
        assertThat(model.getDetails()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        AddressType type = AddressTypeTest.createOne();
        Address first = new Address(type, AddressDetailsTest.createOne());
        Address second = new Address(type, AddressDetailsTest.createOne());
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        Address first = new Address(AddressTypeTest.createOne(), AddressDetailsTest.createOne());
        Address second = new Address(AddressTypeTest.createOne(), AddressDetailsTest.createOne());
        assertThat(second).isNotEqualTo(first);
    }

}
