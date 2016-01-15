package org.ubp.ent.backend.core.model.teacher.contact.address;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void shouldBeEqualByName() {
        AddressType model1 = createOne("Home");
        AddressType model2 = createOne("Home");

        assertThat(model2).isEqualTo(model1);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        AddressType model1 = createOne("Home");
        AddressType model2 = createOne("Secondary Home");

        assertThat(model2).isNotEqualTo(model1);
    }

}