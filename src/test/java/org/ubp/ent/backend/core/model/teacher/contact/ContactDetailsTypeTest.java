package org.ubp.ent.backend.core.model.teacher.contact;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class ContactDetailsTypeTest {

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
        ContactDetailsType type = new ContactDetailsType("Mobile");
        assertThat(type.getId()).isNull();
        assertThat(type.getName()).isEqualTo("Mobile");
    }

    @Test
    public void shouldBeEqualByName() {
        ContactDetailsType model1 = new ContactDetailsType("Home");
        ContactDetailsType model2 = new ContactDetailsType("Home");

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        ContactDetailsType model1 = new ContactDetailsType("Home");
        ContactDetailsType model2 = new ContactDetailsType("Mobile");

        assertThat(model1).isNotEqualTo(model2);
    }

}
