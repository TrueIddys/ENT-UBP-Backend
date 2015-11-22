package org.ubp.ent.backend.model.classroom.equipement;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomEquipmentTest {

    public static RoomEquipment createOne(String equipmentName) {
        return new RoomEquipment(EquipmentTypeTest.createOne(equipmentName), createValidQuantity());
    }

    public static RoomEquipment createOne() {
        return createOne("Default equipment name");
    }

    private static Quantity createValidQuantity() {
        return QuantityTest.createOne();
    }


    @Test
    public void shouldNotInstantiateWithoutEquipmentType() {
        try {
            new RoomEquipment(null, createValidQuantity());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithoutCapacity() {
        try {
            new RoomEquipment(EquipmentTypeTest.createOne(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldConsiderTwoRoomEquipmentEqualsIfTheyHaveTheSameEquipmentType() {
        RoomEquipment equipment1 = createOne();
        RoomEquipment equipment2 = createOne();

        assertThat(equipment1).isEqualTo(equipment2);

        Set<RoomEquipment> equipments = new HashSet<>(2);
        equipments.add(equipment1);
        equipments.add(equipment2);

        assertThat(equipments.size()).isEqualTo(1);
    }

}