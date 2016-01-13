package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailTypeTest {

    public static EmailType createOne() {
        int length = ThreadLocalRandom.current().nextInt(5, 9);
        return createOne(RandomStringUtils.randomAlphabetic(length));
    }

    public static EmailType createOne(String name) {
        return new EmailType(name);
    }

    @Test
    public void shouldInstantiate() {
        EmailType model = createOne("Personnal");
        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Personnal");
    }

}