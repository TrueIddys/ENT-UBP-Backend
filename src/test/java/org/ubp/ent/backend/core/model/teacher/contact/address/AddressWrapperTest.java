package org.ubp.ent.backend.core.model.teacher.contact.address;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressWrapperTest {

    public static AddressWrapper createOne() {
        return new AddressWrapper(AddressTypeTest.createOne(), AddressTest.createOne());
    }

    public static AddressWrapper createOne(String addressType) {
        return new AddressWrapper(AddressTypeTest.createOne(addressType), AddressTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new AddressWrapper(null, AddressTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new AddressWrapper(AddressTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        AddressType type = AddressTypeTest.createOne();
        Address data = AddressTest.createOne();
        AddressWrapper model = new AddressWrapper(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getAddressType()).isEqualTo(type);
        assertThat(model.getAddress()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualWithTypeAndData() {
        AddressType type = AddressTypeTest.createOne();
        Address data = AddressTest.createOne();
        AddressWrapper model1 = new AddressWrapper(type, data);
        AddressWrapper model2 = new AddressWrapper(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        Address data = AddressTest.createOne();
        AddressWrapper model1 = new AddressWrapper(AddressTypeTest.createOne("Home"), data);
        AddressWrapper model2 = new AddressWrapper(AddressTypeTest.createOne("Secondary"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        AddressType type = AddressTypeTest.createOne();
        AddressWrapper model1 = new AddressWrapper(type, AddressTest.createOne("6", "Rue des chaussetiers", "63000", "Clermont-Ferrand"));
        AddressWrapper model2 = new AddressWrapper(type, AddressTest.createOne("156", "Rue des patates", "63000", "Clermont-Ferrand"));
        assertThat(model1).isNotEqualTo(model2);
    }

}