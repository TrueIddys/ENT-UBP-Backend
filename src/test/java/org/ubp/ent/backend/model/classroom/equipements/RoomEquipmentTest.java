package org.ubp.ent.backend.model.classroom.equipements;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomEquipmentTest {

    public static RoomEquipment createValidRoomEquipment() {
        return new RoomEquipment(createValidEquipmentType(), createValidQuantity());
    }

    private static Quantity createValidQuantity() {
        return QuantityTest.createValidQuantity();
    }

    private static EquipmentType createValidEquipmentType() {
        return EquipmentTypeTest.createValidEquipmentType();
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
            new RoomEquipment(createValidEquipmentType(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldConsiderTwoRoomEquipmentAsEqualsIfTheyHaveTheSameEquipmentType() {
        RoomEquipment equipment1 = createValidRoomEquipment();
        RoomEquipment equipment2 = createValidRoomEquipment();

        assertThat(equipment1).isEqualTo(equipment2);

        Set<RoomEquipment> equipments = new HashSet<>(2);
        equipments.add(equipment1);
        equipments.add(equipment2);

        assertThat(equipments.size()).isEqualTo(1);
    }

}