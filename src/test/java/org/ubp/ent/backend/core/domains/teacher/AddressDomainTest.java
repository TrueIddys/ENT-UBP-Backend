package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.Address;
import org.ubp.ent.backend.core.model.teacher.contact.AddressTest;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Anthony on 11/01/2016.
 */
public class AddressDomainTest {

    public static AddressDomain createOne() {
        return new AddressDomain(AddressTest.createOne());
    }

    @Test
    public void shouldCreateDomainFromModel() {
        Address model = AddressTest.createOne();
        AddressDomain domain = new AddressDomain(model);

        assertThat(domain.getStreetNumber()).isEqualTo(model.getStreetNumber());
        assertThat(domain.getStreet()).isEqualTo(model.getStreet());
        assertThat(domain.getZip()).isEqualTo(model.getZip());
        assertThat(domain.getCity()).isEqualTo(model.getCity());
    }

    @Test
    public void shouldTransformDomainToModel() {
        AddressDomain domain = createOne();
        Address model = domain.toModel();

        assertThat(model.getStreetNumber()).isEqualTo(domain.getStreetNumber());
        assertThat(model.getStreet()).isEqualTo(domain.getStreet());
        assertThat(model.getZip()).isEqualTo(domain.getZip());
        assertThat(model.getCity()).isEqualTo(domain.getCity());
    }

    @Test
    public void shouldBeEqualByAllProp() {
        AddressDomain domain = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));
        AddressDomain domain2 = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isEqualTo(domain2);
    }


    @Test
    public void shouldNotBeEqualWithDifferentProp() {
        AddressDomain domain = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));
        AddressDomain domain2 = new AddressDomain(AddressTest.createOne("5", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDomain(AddressTest.createOne("6", "abcd", "63000", "Paris"));
        domain2 = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63100", "Paris"));
        domain2 = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Lyon"));
        domain2 = new AddressDomain(AddressTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);
    }

}