package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailTest {

    public static Email createOne() {
        return new Email(EmailTypeTest.createOne(), EmailDetailsTest.createOne());
    }

    public static Email createOne(String EmailType) {
        return new Email(EmailTypeTest.createOne(EmailType), EmailDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new Email(null, EmailDetailsTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new Email(EmailTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        EmailType type = EmailTypeTest.createOne();
        EmailDetails data = EmailDetailsTest.createOne();
        Email model = new Email(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getEmailType()).isEqualTo(type);
        assertThat(model.getEmailDetails()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualWithTypeAndData() {
        EmailType type = EmailTypeTest.createOne();
        EmailDetails data = EmailDetailsTest.createOne();
        Email model1 = new Email(type, data);
        Email model2 = new Email(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        EmailDetails data = EmailDetailsTest.createOne();
        Email model1 = new Email(EmailTypeTest.createOne("Personal"), data);
        Email model2 = new Email(EmailTypeTest.createOne("Work"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        EmailType type = EmailTypeTest.createOne();
        Email model1 = new Email(type, EmailDetailsTest.createOne());
        Email model2 = new Email(type, EmailDetailsTest.createOne("john-doe@jdoe.com"));
        assertThat(model1).isNotEqualTo(model2);
    }

}