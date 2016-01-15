package org.ubp.ent.backend.core.model.teacher.contact;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class ContactDetailsTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new DefaultContactDetailsType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new DefaultContactDetailsType(" ");
    }

    @Test
    public void shouldInstantiate() {
        DefaultContactDetailsType type = new DefaultContactDetailsType("Mobile");
        assertThat(type.getId()).isNull();
        assertThat(type.getName()).isEqualTo("Mobile");
    }

    @Test
    public void shouldBeEqualByName() {
        DefaultContactDetailsType model1 = new DefaultContactDetailsType("Home");
        DefaultContactDetailsType model2 = new DefaultContactDetailsType("Home");

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        DefaultContactDetailsType model1 = new DefaultContactDetailsType("Home");
        DefaultContactDetailsType model2 = new DefaultContactDetailsType("Mobile");

        assertThat(model1).isNotEqualTo(model2);
    }

    private static class DefaultContactDetailsType extends ContactDetailsType {

        public DefaultContactDetailsType(String name) {
            super(name);
        }
    }

}
