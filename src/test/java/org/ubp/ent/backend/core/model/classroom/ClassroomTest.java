package org.ubp.ent.backend.core.model.classroom;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipmentTest;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 20/11/2015.
 */
public class ClassroomTest {

    public static Classroom createOne(String name) {
        return new Classroom(name, createValidCapacity(), createValidClassroomTypeSet());
    }

    private static Set<ClassroomType> createValidClassroomTypeSet() {
        return Sets.newHashSet(ClassroomType.CM, ClassroomType.TD);
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
    public void shouldInstantiate() {
        Classroom classroom = createOne();

        assertThat(classroom.getName()).isEqualTo(createValidName());
        assertThat(classroom.getRoomCapacity()).isEqualTo(createValidCapacity());
        assertThat(classroom.getEquipments()).isEmpty();
        assertThat(classroom.getTypes()).isNotEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new Classroom(null, createValidCapacity(), createValidClassroomTypeSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Classroom(" ", createValidCapacity(), createValidClassroomTypeSet());
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullCapacity() {
        new Classroom(createValidName(), null, createValidClassroomTypeSet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenAddingANullEquipment() {
        Classroom classroom = createOne();
        classroom.addEquipment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullClassroomTypes() {
        new Classroom(createValidName(), createValidCapacity(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyClassroomTypes() {
        new Classroom(createValidName(), createValidCapacity(), Sets.newHashSet());
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
