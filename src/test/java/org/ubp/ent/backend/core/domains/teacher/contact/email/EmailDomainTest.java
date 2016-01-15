package org.ubp.ent.backend.core.domains.teacher.contact.email;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTest;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class EmailDomainTest {

    public static EmailDomain createOne() {
        return new EmailDomain(EmailTest.createOne());
    }

    public static EmailDomain createOne(String EmailTypeDomain) {
        return new EmailDomain(EmailTest.createOne(EmailTypeDomain));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new EmailDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Email model = EmailTest.createOne();
        model.setId(12L);
        EmailDomain domain = new EmailDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getType().toModel()).isEqualTo(model.getType());
        assertThat(domain.getDetails().toModel()).isEqualTo(model.getDetails());
    }

    @Test
    public void shouldTransformToModel() {
        EmailDomain domain = createOne();
        domain.setId(12L);
        Email model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getType()).isEqualTo(domain.getType().toModel());
        assertThat(model.getDetails()).isEqualTo(domain.getDetails().toModel());
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        EmailDomain first = createOne("Personal");
        EmailDomain second = createOne("Personal");
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        EmailDomain first = createOne("Personal");
        EmailDomain second = createOne("Work");
        assertThat(second).isNotEqualTo(first);
    }

}