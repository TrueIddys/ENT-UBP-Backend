package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneTest {

    public static Phone createOne() {
        return new Phone(PhoneDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new Phone(null);
    }

    @Test
    public void shouldInstantiate() {
        PhoneDetails data = PhoneDetailsTest.createOne();
        Phone model = new Phone(data);

        assertThat(model.getId()).isNull();
        assertThat(model.getDetails()).isEqualTo(data);
    }

}
