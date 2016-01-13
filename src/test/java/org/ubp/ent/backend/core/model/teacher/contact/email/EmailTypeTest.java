package org.ubp.ent.backend.core.model.teacher.contact.email;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 13/01/2016.
 */
public class EmailTypeTest {

    public static EmailType createOne() {
        return new EmailType("University");
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