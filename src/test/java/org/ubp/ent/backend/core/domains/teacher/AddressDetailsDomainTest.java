package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDetailsDomain;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetailsTest;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Anthony on 11/01/2016.
 */
public class AddressDetailsDomainTest {

    public static AddressDetailsDomain createOne() {
        return new AddressDetailsDomain(AddressDetailsTest.createOne());
    }

    @Test
    public void shouldCreateDomainFromModel() {
        AddressDetails model = AddressDetailsTest.createOne();
        AddressDetailsDomain domain = new AddressDetailsDomain(model);

        assertThat(domain.getStreetNumber()).isEqualTo(model.getStreetNumber());
        assertThat(domain.getStreet()).isEqualTo(model.getStreet());
        assertThat(domain.getZip()).isEqualTo(model.getZip());
        assertThat(domain.getCity()).isEqualTo(model.getCity());
    }

    @Test
    public void shouldTransformDomainToModel() {
        AddressDetailsDomain domain = createOne();
        AddressDetails model = domain.toModel();

        assertThat(model.getStreetNumber()).isEqualTo(domain.getStreetNumber());
        assertThat(model.getStreet()).isEqualTo(domain.getStreet());
        assertThat(model.getZip()).isEqualTo(domain.getZip());
        assertThat(model.getCity()).isEqualTo(domain.getCity());
    }

    @Test
    public void shouldBeEqualByAllProp() {
        AddressDetailsDomain domain = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));
        AddressDetailsDomain domain2 = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isEqualTo(domain2);
    }


    @Test
    public void shouldNotBeEqualWithDifferentProp() {
        AddressDetailsDomain domain = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));
        AddressDetailsDomain domain2 = new AddressDetailsDomain(AddressDetailsTest.createOne("5", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "abcd", "63000", "Paris"));
        domain2 = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63100", "Paris"));
        domain2 = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Lyon"));
        domain2 = new AddressDetailsDomain(AddressDetailsTest.createOne("6", "sqdqs", "63000", "Paris"));

        assertThat(domain).isNotEqualTo(domain2);
    }

}