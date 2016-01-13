package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.Name;
import org.ubp.ent.backend.core.model.teacher.NameTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class NameDomainTest {

    public static NameDomain createOne() {
        return new NameDomain(NameTest.createOne());
    }

    @Test
    public void shouldCreateDomainFromModel() {
        Name model = NameTest.createOne();
        NameDomain domain = new NameDomain(model);

        assertThat(domain.getFirstName()).isEqualTo(model.getFirstName());
        assertThat(domain.getLastName()).isEqualTo(model.getLastName());
    }

    @Test
    public void shouldTransformDomainToModel() {
        NameDomain domain = createOne();
        Name model = domain.toModel();

        assertThat(model.getFirstName()).isEqualTo(domain.getFirstName());
        assertThat(model.getLastName()).isEqualTo(domain.getLastName());
    }

    @Test
    public void shouldBeEqualByAllProp() {
        NameDomain domain = new NameDomain(new Name("Anthony", "Raymond"));
        NameDomain domain2 = new NameDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isEqualTo(domain2);
    }


    @Test
    public void shouldNotBeEqualWithDifferentProp() {
        NameDomain domain = new NameDomain(new Name("John", "Raymond"));
        NameDomain domain2 = new NameDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isNotEqualTo(domain2);

        domain = new NameDomain(new Name("Anthony", "Doe"));
        domain2 = new NameDomain(new Name("Anthony", "Raymond"));

        assertThat(domain).isNotEqualTo(domain2);
    }

}