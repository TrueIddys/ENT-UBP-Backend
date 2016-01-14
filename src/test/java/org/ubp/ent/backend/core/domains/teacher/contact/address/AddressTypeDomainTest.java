package org.ubp.ent.backend.core.domains.teacher.contact.address;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTypeTest;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressTypeDomainTest {

    public static AddressTypeDomain createOne() {
        return new AddressTypeDomain(AddressTypeTest.createOne());
    }

    public static AddressTypeDomain createOne(String name) {
        return new AddressTypeDomain(AddressTypeTest.createOne(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new AddressTypeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        AddressType model = AddressTypeTest.createOne();
        model.setId(12L);
        AddressTypeDomain domain = new AddressTypeDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldTransformToModel() {
        AddressTypeDomain domain = new AddressTypeDomain(AddressTypeTest.createOne());
        domain.setId(12L);
        ContactDetailsType model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName());
    }


    @Test
    public void shouldBeEqualByName() {
        AddressTypeDomain domain1 = createOne("Home");
        AddressTypeDomain domain2 = createOne("Home");

        assertThat(domain2).isEqualTo(domain1);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        AddressTypeDomain domain1 = createOne("Home");
        AddressTypeDomain domain2 = createOne("Secondary Home");

        assertThat(domain2).isNotEqualTo(domain1);
    }

}