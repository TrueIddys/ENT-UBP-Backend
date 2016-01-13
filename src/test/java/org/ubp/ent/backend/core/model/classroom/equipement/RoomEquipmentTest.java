package org.ubp.ent.backend.core.model.classroom.equipement;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomEquipmentTest {

    public static RoomEquipment createOne(String equipmentName) {
        return new RoomEquipment(EquipmentTypeTest.createOne(equipmentName), createValidQuantity());
    }

    public static RoomEquipment createOne() {
        int length = ThreadLocalRandom.current().nextInt(9, 15);
        String name = RandomStringUtils.randomAlphabetic(length);
        return createOne(name);
    }

    private static Quantity createValidQuantity() {
        return QuantityTest.createOne();
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithoutEquipmentType() {
        new RoomEquipment(null, createValidQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithoutCapacity() {
        new RoomEquipment(EquipmentTypeTest.createOne(), null);
    }

    @Test
    public void shouldConsiderTwoRoomEquipmentEqualsIfTheyHaveTheSameEquipmentType() {
        RoomEquipment equipment1 = createOne("equipment-name-duplicated");
        RoomEquipment equipment2 = createOne("equipment-name-duplicated");

        assertThat(equipment1).isEqualTo(equipment2);

        Set<RoomEquipment> equipments = new HashSet<>(2);
        equipments.add(equipment1);
        equipments.add(equipment2);

        assertThat(equipments.size()).isEqualTo(1);
    }

}