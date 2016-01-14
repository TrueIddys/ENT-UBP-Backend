package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneTypeTest {

    public static PhoneType createOne() {
        int length = ThreadLocalRandom.current().nextInt(5, 9);
        return createOne(RandomStringUtils.randomAlphabetic(length));
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

    @Test
    public void shouldBeEqualByName() {
        PhoneType model1 = createOne("Home");
        PhoneType model2 = createOne("Home");

        assertThat(model2).isEqualTo(model1);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        PhoneType model1 = createOne("Home");
        PhoneType model2 = createOne("Work");

        assertThat(model2).isNotEqualTo(model1);
    }

}