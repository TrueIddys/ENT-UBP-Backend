package org.ubp.ent.backend.core.domains.teacher.contact.address;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class AddressDomainTest {

    public static AddressDomain createOne() {
        return new AddressDomain(AddressTest.createOne());
    }

    public static AddressDomain createOne(String AddressTypeDomain) {
        return new AddressDomain(AddressTest.createOne(AddressTypeDomain));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new AddressDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Address model = AddressTest.createOne();
        model.setId(12L);
        AddressDomain domain = new AddressDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getType().toModel()).isEqualTo(model.getType());
        assertThat(domain.getDetails().toModel()).isEqualTo(model.getDetails());
    }

    @Test
    public void shouldTransformToModel() {
        AddressDomain domain = createOne();
        domain.setId(12L);
        Address model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getType()).isEqualTo(domain.getType().toModel());
        assertThat(model.getDetails()).isEqualTo(domain.getDetails().toModel());
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        AddressDomain first = createOne("Home");
        AddressDomain second = createOne("Home");
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        AddressDomain first = createOne("Home");
        AddressDomain second = createOne("Secondary Home");
        assertThat(second).isNotEqualTo(first);
    }

}
