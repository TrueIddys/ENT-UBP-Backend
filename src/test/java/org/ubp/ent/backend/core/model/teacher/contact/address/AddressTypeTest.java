package org.ubp.ent.backend.core.model.teacher.contact.address;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 13/01/2016.
 */
public class AddressTypeTest {

    public static AddressType createOne() {
        int length = ThreadLocalRandom.current().nextInt(5, 9);
        return createOne(RandomStringUtils.randomAlphabetic(length));
    }

    public static AddressType createOne(String name) {
        return new AddressType(name);
    }

    @Test
    public void shouldInstantiate() {
        AddressType model = createOne("Home");
        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Home");
    }

}