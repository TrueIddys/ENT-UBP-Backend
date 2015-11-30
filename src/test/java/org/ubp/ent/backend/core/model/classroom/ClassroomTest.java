package org.ubp.ent.backend.core.model.classroom;

import org.junit.Test;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipmentTest;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class ClassroomTest {

    public static Classroom createOne(String name) {
        return new Classroom(name, createValidCapacity());
    }

    public static Classroom createOne() {
        return createOne(createValidName());
    }

    private static String createValidName() {
        return "Default name";
    }

    private static RoomCapacity createValidCapacity() {
        return RoomCapacityTest.createOne();
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
        Classroom classroom = createOne();
        try {
            classroom.addEquipment(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldAddEquipmentToSet() {
        RoomEquipment equipment = RoomEquipmentTest.createOne();

        Classroom classroom = createOne();
        int equipmentSize = classroom.getEquipments().size();
        classroom.addEquipment(equipment);

        assertThat(classroom.getEquipments().size()).isEqualTo(equipmentSize + 1);
    }

    @Test
    public void shouldBeEqualByName() {
        Classroom classroom = ClassroomTest.createOne("SL5");
        Classroom classroom2 = ClassroomTest.createOne("SL5");

        assertThat(classroom2).isEqualTo(classroom);
    }

}
