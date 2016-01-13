package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.Name;
import org.ubp.ent.backend.core.model.teacher.NameTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class NameDetailsDomainTest {

    public static NameDetailsDomain createOne() {
        return new NameDetailsDomain(NameTest.createOne());
    }

    @Test
    public void shouldCreateDomainFromModel() {
        Name model = NameTest.createOne();
        NameDetailsDomain domain = new NameDetailsDomain(model);

        assertThat(domain.getFirstName()).isEqualTo(model.getFirstName());
        assertThat(domain.getLastName()).isEqualTo(model.getLastName());
    }

    @Test
    public void shouldTransformDomainToModel() {
        NameDetailsDomain domain = createOne();
        Name model = domain.toModel();

        assertThat(model.getFirstName()).isEqualTo(domain.getFirstName());
        assertThat(model.getLastName()).isEqualTo(domain.getLastName());
    }

    @Test
    public void shouldBeEqualByAllProp() {
        NameDetailsDomain domain = new NameDetailsDomain(new Name("Anthony", "Raymond"));
        NameDetailsDomain domain2 = new NameDetailsDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isEqualTo(domain2);
    }


    @Test
    public void shouldNotBeEqualWithDifferentProp() {
        NameDetailsDomain domain = new NameDetailsDomain(new Name("John", "Raymond"));
        NameDetailsDomain domain2 = new NameDetailsDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new NameDetailsDomain(new Name("Anthony", "Doe"));
        domain2 = new NameDetailsDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isNotEqualTo(domain2);
    }

}