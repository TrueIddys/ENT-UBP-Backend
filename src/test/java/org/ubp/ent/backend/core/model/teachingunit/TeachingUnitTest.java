package org.ubp.ent.backend.core.model.teachingunit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnitTest {

    public static TeachingUnit createOne(String name) {
        return new TeachingUnit(name);
    }

    public static TeachingUnit createOne() {
        return new TeachingUnit(createValidName());
    }

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(9, 15);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }


    @Test
    public void shouldInstantiate() {
        TeachingUnit teachingUnit = createOne();

        assertThat(teachingUnit.getName()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new TeachingUnit(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new TeachingUnit(" ");
    }

    @Test
    public void shouldBeEqualById() {
        TeachingUnit first = TeachingUnitTest.createOne();
        first.setId(1L);
        TeachingUnit second = TeachingUnitTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        TeachingUnit first = TeachingUnitTest.createOne();
        first.setId(1L);
        TeachingUnit second = TeachingUnitTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        TeachingUnit first = TeachingUnitTest.createOne();
        TeachingUnit second = TeachingUnitTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }
}
