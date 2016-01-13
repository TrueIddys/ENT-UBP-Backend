package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneTest {

    public static Phone createOne() {
        return new Phone(PhoneTypeTest.createOne(), PhoneDetailsTest.createOne());
    }

    public static Phone createOne(String PhoneType) {
        return new Phone(PhoneTypeTest.createOne(PhoneType), PhoneDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new Phone(null, PhoneDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new Phone(PhoneTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        PhoneType type = PhoneTypeTest.createOne();
        PhoneDetails data = PhoneDetailsTest.createOne();
        Phone model = new Phone(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getPhoneType()).isEqualTo(type);
        assertThat(model.getPhoneDetails()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualWithTypeAndData() {
        PhoneType type = PhoneTypeTest.createOne();
        PhoneDetails data = PhoneDetailsTest.createOne();
        Phone model1 = new Phone(type, data);
        Phone model2 = new Phone(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        PhoneDetails data = PhoneDetailsTest.createOne();
        Phone model1 = new Phone(PhoneTypeTest.createOne("Personal"), data);
        Phone model2 = new Phone(PhoneTypeTest.createOne("Work"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        PhoneType type = PhoneTypeTest.createOne();
        Phone model1 = new Phone(type, PhoneDetailsTest.createOne());
        Phone model2 = new Phone(type, PhoneDetailsTest.createOne());
        assertThat(model1).isNotEqualTo(model2);
    }
    
}