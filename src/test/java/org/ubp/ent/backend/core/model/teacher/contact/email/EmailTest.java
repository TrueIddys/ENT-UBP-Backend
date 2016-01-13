package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.model.BadFormattedEmailAddress;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.fail;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailTest {

    private static final String VALID_EMAIL = "aaa-rrr@ent-ubp.fr";

    public static Email createOne() {
        return createOne(VALID_EMAIL);
    }

    public static Email createOne(String email) {
        return new Email(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullNumber() {
        new Email(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyNumber() {
        new Email(" ");
    }

    @Test
    public void shouldInstantiate() {
        Email model = new Email(VALID_EMAIL);
        assertThat(model.getAddress()).isEqualTo(VALID_EMAIL);
    }

    @Test
    public void shouldRejectNonPhoneNumber() {
        List<String> wrongMails = Arrays.asList(
                "abcdefghij",
                "@ent-ubp.fr",
                "aa@ent-ubp",
                "aa@fr"
        );
        for (String mail : wrongMails) {
            try {
                new Email(mail);
                fail("'" + mail + "' should not be a valid email address");
            } catch (BadFormattedEmailAddress e) {
                assertThat(e.getMessage()).isNotEmpty();
            }
        }
    }


    @Test
    public void shouldBeEqualByNumber() {
        assertThat(createOne()).isEqualTo(createOne());
    }

    @Test
    public void shouldNotBeEqualWithDifferentNumbers() {
        assertThat(createOne()).isNotEqualTo(new Email("pomme-api@ent-ubp.fr"));
    }

}