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
        assertThat(model.getType()).isEqualTo(type);
        assertThat(model.getDetails()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        PhoneType type = PhoneTypeTest.createOne();
        Phone first = new Phone(type, PhoneDetailsTest.createOne());
        Phone second = new Phone(type, PhoneDetailsTest.createOne());
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        Phone first = new Phone(PhoneTypeTest.createOne(), PhoneDetailsTest.createOne());
        Phone second = new Phone(PhoneTypeTest.createOne(), PhoneDetailsTest.createOne());
        assertThat(second).isNotEqualTo(first);
    }

}