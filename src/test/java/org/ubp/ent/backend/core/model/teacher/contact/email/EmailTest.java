package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailTest {

    public static Email createOne() {
        return createOne(EmailTypeTest.createOne());
    }

    public static Email createOne(String EmailType) {
        return createOne(EmailTypeTest.createOne(EmailType));
    }

    public static Email createOne(EmailType type) {
        return new Email(type, EmailDetailsTest.createOne());
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
        assertThat(model.getType()).isEqualTo(type);
        assertThat(model.getDetails()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        EmailType type = EmailTypeTest.createOne();
        Email first = new Email(type, EmailDetailsTest.createOne());
        Email second = new Email(type, EmailDetailsTest.createOne());
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        Email first = new Email(EmailTypeTest.createOne(), EmailDetailsTest.createOne());
        Email second = new Email(EmailTypeTest.createOne(), EmailDetailsTest.createOne());
        assertThat(second).isNotEqualTo(first);
    }

}
