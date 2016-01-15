package org.ubp.ent.backend.core.domains.teacher.contact;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class ContactDetailsTypeDomainTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullContactDetailsType() {
        new DefaultContactDetailsTypeDomain(null);
    }

    @Test
    public void shouldInstantiate() {
        DefaultContactDetailsTypeDomain type = new DefaultContactDetailsTypeDomain(new ContactDetailsType("Mobile"));
        assertThat(type.getId()).isNull();
        assertThat(type.getName()).isEqualTo("Mobile");
    }

    @Test
    public void shouldBeEqualByName() {
        ContactDetailsType cdt1 = new ContactDetailsType("Mobile");
        ContactDetailsType cdt2 = new ContactDetailsType("Mobile");
        DefaultContactDetailsTypeDomain model1 = new DefaultContactDetailsTypeDomain(cdt1);
        DefaultContactDetailsTypeDomain model2 = new DefaultContactDetailsTypeDomain(cdt2);

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        ContactDetailsType cdt1 = new ContactDetailsType("Mobile");
        ContactDetailsType cdt2 = new ContactDetailsType("Home");
        DefaultContactDetailsTypeDomain model1 = new DefaultContactDetailsTypeDomain(cdt1);
        DefaultContactDetailsTypeDomain model2 = new DefaultContactDetailsTypeDomain(cdt2);

        assertThat(model1).isNotEqualTo(model2);
    }


    private static class DefaultContactDetailsTypeDomain extends ContactDetailsTypeDomain {

        public DefaultContactDetailsTypeDomain(ContactDetailsType contactDetailsType) {
            super(contactDetailsType);
        }
    }

}