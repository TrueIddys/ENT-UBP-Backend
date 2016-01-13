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

    @Test
    public void shouldBeEqualWithTypeAndData() {
        AddressType type = AddressTypeTest.createOne();
        AddressDetails data = AddressDetailsTest.createOne();
        Address model1 = new Address(type, data);
        Address model2 = new Address(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        AddressDetails data = AddressDetailsTest.createOne();
        Address model1 = new Address(AddressTypeTest.createOne("Home"), data);
        Address model2 = new Address(AddressTypeTest.createOne("Secondary"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        AddressType type = AddressTypeTest.createOne();
        Address model1 = new Address(type, AddressDetailsTest.createOne("6", "Rue des chaussetiers", "63000", "Clermont-Ferrand"));
        Address model2 = new Address(type, AddressDetailsTest.createOne("156", "Rue des patates", "63000", "Clermont-Ferrand"));
        assertThat(model1).isNotEqualTo(model2);
    }

}