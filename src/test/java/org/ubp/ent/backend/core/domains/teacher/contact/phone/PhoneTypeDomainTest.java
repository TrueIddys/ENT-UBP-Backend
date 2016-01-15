package org.ubp.ent.backend.core.domains.teacher.contact.phone;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTypeTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class PhoneTypeDomainTest {

    public static PhoneTypeDomain createOne() {
        return new PhoneTypeDomain(PhoneTypeTest.createOne());
    }

    public static PhoneTypeDomain createOne(String name) {
        return new PhoneTypeDomain(PhoneTypeTest.createOne(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new PhoneTypeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        PhoneType model = PhoneTypeTest.createOne();
        model.setId(12L);
        PhoneTypeDomain domain = new PhoneTypeDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldTransformToModel() {
        PhoneTypeDomain domain = new PhoneTypeDomain(PhoneTypeTest.createOne());
        domain.setId(12L);
        ContactDetailsType model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName());
    }


    @Test
    public void shouldBeEqualByName() {
        PhoneTypeDomain domain1 = createOne("Home");
        PhoneTypeDomain domain2 = createOne("Home");

        assertThat(domain2).isEqualTo(domain1);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        PhoneTypeDomain domain1 = createOne("Home");
        PhoneTypeDomain domain2 = createOne("Work");

        assertThat(domain2).isNotEqualTo(domain1);
    }

}