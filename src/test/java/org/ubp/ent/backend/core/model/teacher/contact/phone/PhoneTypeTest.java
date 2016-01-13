package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneTypeTest {

    public static PhoneType createOne() {
        return new PhoneType("Mobile");
    }

    public static PhoneType createOne(String name) {
        return new PhoneType(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new PhoneType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new PhoneType(" ");
    }

    @Test
    public void shouldInstantiate() {
        PhoneType type = createOne("Mobile");
        assertThat(type.getId()).isNull();
        assertThat(type.getName()).isEqualTo("Mobile");
    }

    @Test
    public void shouldBeEqualByName() {
        PhoneType model1 = createOne("Home");
        PhoneType model2 = createOne("Home");

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        PhoneType model1 = createOne("Home");
        PhoneType model2 = createOne("Mobile");

        assertThat(model1).isNotEqualTo(model2);
    }


}