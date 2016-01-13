package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneTypeTest {

    public static PhoneType createOne() {
        return new PhoneType("Mobile");
    }

    public static PhoneType createOne(String name) {
        return new PhoneType(name);
    }

    @Test
    public void shouldInstantiate() {
        PhoneType model = createOne("Mobile");
        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Mobile");
    }

}