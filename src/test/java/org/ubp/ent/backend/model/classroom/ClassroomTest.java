package org.ubp.ent.backend.model.classroom;

import org.junit.Test;
import org.ubp.ent.backend.model.classroom.equipements.RoomEquipment;
import org.ubp.ent.backend.model.classroom.equipements.RoomEquipmentTest;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class ClassroomTest {

    public static Classroom createValidClassroom() {
        return new Classroom(createValidName(), createValidCapacity());
    }

    private static String createValidName() {
        return "Default name";
    }

    private static RoomCapacity createValidCapacity() {
        return RoomCapacityTest.createValidRoomCapacity();
    }

    @Test
    public void shouldNotInstantiateWithEmptyName() {
        try {
            new Classroom(null, createValidCapacity());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithNullName() {
        try {
            new Classroom(" ", createValidCapacity());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }


    @Test
    public void shouldNotInstantiateWithNullCapacity() {
        try {
            new Classroom(createValidName(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldFailWhenAddingANullEquipment() {
        Classroom classroom = createValidClassroom();
        try {
            classroom.addEquipment(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldAddEquipmentToSet() {
        RoomEquipment equipment = RoomEquipmentTest.createValidRoomEquipment();

        Classroom classroom = createValidClassroom();
        int equipmentSize = classroom.getEquipments().size();
        classroom.addEquipment(equipment);

        assertThat(classroom.getEquipments().size()).isEqualTo(equipmentSize + 1);
    }

}
