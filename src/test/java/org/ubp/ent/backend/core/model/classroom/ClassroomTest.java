package org.ubp.ent.backend.core.model.classroom;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipmentTest;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 20/11/2015.
 */
public class ClassroomTest {

    public static Classroom createOne(String name) {
        Classroom classroom = createOneEmpty(name);
        classroom.addEquipment(RoomEquipmentTest.createOne());
        classroom.addEquipment(RoomEquipmentTest.createOne());

        return classroom;
    }

    public static Classroom createOneEmpty(String name) {
        return new Classroom(name, RoomCapacityTest.createOne(), createValidClassroomTypeSet());
    }

    private static Set<ClassroomType> createValidClassroomTypeSet() {
        return Sets.newHashSet(ClassroomType.CM, ClassroomType.TD);
    }

    public static Classroom createOne() {
        return createOne(createValidName());
    }
    public static Classroom createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(9, 15);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }

    private static RoomCapacity createValidCapacity() {
        return RoomCapacityTest.createOne();
    }

    @Test
    public void shouldInstantiate() {
        Classroom classroom = createOneEmpty();

        assertThat(classroom.getId()).isNull();
        assertThat(classroom.getName()).isNotNull();
        assertThat(classroom.getRoomCapacity()).isNotNull();
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

        Classroom classroom = createOneEmpty();
        classroom.addEquipment(equipment);

        assertThat(classroom.getEquipments()).containsOnly(equipment);
    }

    @Test
    public void shouldReplaceOldEquipmentIfEquipmentTypeAlreadyInList() {
        Classroom classroom = createOneEmpty();

        RoomEquipment equipment1 = RoomEquipmentTest.createOne("duplicated-equipment");
        classroom.addEquipment(equipment1);

        RoomEquipment equipment2 = RoomEquipmentTest.createOne("duplicated-equipment");
        classroom.addEquipment(equipment2);

        assertThat(classroom.getEquipments()).containsOnly(equipment2);
    }

    @Test
    public void shouldBeEqualByName() {
        Classroom classroom = ClassroomTest.createOne("SL5");
        Classroom classroom2 = ClassroomTest.createOne("SL5");

        assertThat(classroom2).isEqualTo(classroom);
    }

    @Test
    public void shouldNotBeEqualWithDifferentNames() {
        Classroom classroom = ClassroomTest.createOne("SL5");
        Classroom classroom2 = ClassroomTest.createOne("SL6");

        assertThat(classroom2).isNotEqualTo(classroom);
    }

}
