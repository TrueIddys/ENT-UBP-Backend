package org.ubp.ent.backend.core.domains.teacher.contact.email;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTypeTest;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class EmailTypeDomainTest {

    public static EmailTypeDomain createOne() {
        return new EmailTypeDomain(EmailTypeTest.createOne());
    }

    public static EmailTypeDomain createOne(String name) {
        return new EmailTypeDomain(EmailTypeTest.createOne(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new EmailTypeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        EmailType model = EmailTypeTest.createOne();
        model.setId(12L);
        EmailTypeDomain domain = new EmailTypeDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldTransformToModel() {
        EmailTypeDomain domain = new EmailTypeDomain(EmailTypeTest.createOne());
        domain.setId(12L);
        ContactDetailsType model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName());
    }


    @Test
    public void shouldBeEqualByName() {
        EmailTypeDomain domain1 = createOne("Personal");
        EmailTypeDomain domain2 = createOne("Personal");

        assertThat(domain2).isEqualTo(domain1);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        EmailTypeDomain domain1 = createOne("Personal");
        EmailTypeDomain domain2 = createOne("Work");

        assertThat(domain2).isNotEqualTo(domain1);
    }

}