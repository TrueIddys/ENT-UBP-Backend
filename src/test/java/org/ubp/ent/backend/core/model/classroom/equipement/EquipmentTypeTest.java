package org.ubp.ent.backend.core.model.classroom.equipement;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentTypeTest {

    public static EquipmentType createOne() {
        int length = ThreadLocalRandom.current().nextInt(7, 10);
        String name = RandomStringUtils.randomAlphabetic(length);
        return createOne(name);
    }

    public static EquipmentType createOne(String name) {
        return new EquipmentType(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new EquipmentType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new EquipmentType(" ");
    }

    @Test
    public void shouldBeEqualByName() {
        EquipmentType eq1 = createOne("name 1");
        EquipmentType eq2 = createOne("name 1");

        assertThat(eq1).isEqualTo(eq2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        EquipmentType eq1 = createOne("name 1");
        EquipmentType eq2 = createOne("name 2");

        assertThat(eq1).isNotEqualTo(eq2);
    }

}
