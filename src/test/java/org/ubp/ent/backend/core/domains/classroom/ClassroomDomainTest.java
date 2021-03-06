package org.ubp.ent.backend.core.domains.classroom;


import com.google.common.collect.Sets;
import org.junit.Test;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomainTest;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipmentTest;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 22/11/2015.
 */
public class ClassroomDomainTest {

    public static ClassroomDomain createOne(String name, int equipmentCount) {
        Classroom classroom = ClassroomTest.createOne(name);
        ClassroomDomain domain = new ClassroomDomain(classroom);

        for (int i = 0; i < equipmentCount; i++) {
            domain.addEquipment(RoomEquipmentDomainTest.createOne("equipment " + i, domain));
        }
        return domain;
    }

    public static ClassroomDomain createOne(int equipmentCount) {
        return createOne("Default classroom name", equipmentCount);
    }

    private static Set<ClassroomType> createValidClassroomTypeSet() {
        return Sets.newHashSet(ClassroomType.CM, ClassroomType.TD);
    }

    public static ClassroomDomain createOne(String name) {
        return createOne(name, 0);
    }

    public static ClassroomDomain createOne() {
        return createOne(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new ClassroomDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Classroom model = ClassroomTest.createOne();
        model.setId(12L);
        ClassroomDomain domain = new ClassroomDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        assertThat(domain.getRoomCapacity()).isEqualTo(model.getRoomCapacity().getMaxCapacity());
        // Model to domain should not transform equipments
        assertThat(domain.getEquipments()).isEmpty();
        assertThat(domain.getTypes()).isEqualTo(model.getTypes());
    }

    @Test
    public void shouldTransformToModel() {
        Classroom model = ClassroomTest.createOne();
        model.setId(12L);
        ClassroomDomain domain = new ClassroomDomain(model);

        Classroom newModel = domain.toModel();
        assertThat(newModel.getId()).isEqualTo(model.getId());
        assertThat(newModel.getName()).isEqualTo(model.getName());
        assertThat(newModel.getRoomCapacity()).isEqualTo(model.getRoomCapacity());
        assertThat(newModel.getTypes()).isEqualTo(model.getTypes());
        assertThat(newModel.getEquipments()).isEmpty();
    }

    @Test
    public void shouldNotPopulateListOfEquipmentWhenCreatingDomainFromModel() {
        Classroom model = ClassroomTest.createOne();
        model.addEquipment(RoomEquipmentTest.createOne());
        ClassroomDomain domain = new ClassroomDomain(model);

        assertThat(domain.getEquipments()).isEmpty();
    }

    @Test
    public void shouldNotPopulateListOfEquipmentWhenTransformingDomainFromModel() {
        Classroom model = ClassroomTest.createOne();
        ClassroomDomain domain = new ClassroomDomain(model);
        domain.getEquipments().add(new RoomEquipmentDomain(RoomEquipmentTest.createOne(), domain));

        Classroom domainToModel = domain.toModel();
        assertThat(domainToModel.getEquipments()).isEmpty();
    }

    @Test
    public void shouldBeEqualByName() {
        ClassroomDomain domain = ClassroomDomainTest.createOne("SL5");
        ClassroomDomain domain2 = ClassroomDomainTest.createOne("SL5");

        assertThat(domain2).isEqualTo(domain);
    }

    @Test
    public void shouldNotBeEqualWithDifferentName() {
        ClassroomDomain domain = ClassroomDomainTest.createOne("SL5");
        ClassroomDomain domain2 = ClassroomDomainTest.createOne("SL6");

        assertThat(domain2).isNotEqualTo(domain);
    }

}
