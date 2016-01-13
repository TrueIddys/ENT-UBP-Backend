package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailWrapperTest {

    public static EmailWrapper createOne() {
        return new EmailWrapper(EmailTypeTest.createOne(), EmailTest.createOne());
    }

    public static EmailWrapper createOne(String EmailType) {
        return new EmailWrapper(EmailTypeTest.createOne(EmailType), EmailTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new EmailWrapper(null, EmailTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new EmailWrapper(EmailTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        EmailType type = EmailTypeTest.createOne();
        Email data = EmailTest.createOne();
        EmailWrapper model = new EmailWrapper(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getEmailType()).isEqualTo(type);
        assertThat(model.getEmail()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualWithTypeAndData() {
        EmailType type = EmailTypeTest.createOne();
        Email data = EmailTest.createOne();
        EmailWrapper model1 = new EmailWrapper(type, data);
        EmailWrapper model2 = new EmailWrapper(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        Email data = EmailTest.createOne();
        EmailWrapper model1 = new EmailWrapper(EmailTypeTest.createOne("Personal"), data);
        EmailWrapper model2 = new EmailWrapper(EmailTypeTest.createOne("Work"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        EmailType type = EmailTypeTest.createOne();
        EmailWrapper model1 = new EmailWrapper(type, EmailTest.createOne());
        EmailWrapper model2 = new EmailWrapper(type, EmailTest.createOne("john-doe@jdoe.com"));
        assertThat(model1).isNotEqualTo(model2);
    }

}