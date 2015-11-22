package org.ubp.ent.backend.domains.classroom;


import org.junit.Test;
import org.ubp.ent.backend.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.domains.classroom.equipement.RoomEquipmentDomainTest;
import org.ubp.ent.backend.model.classroom.Classroom;
import org.ubp.ent.backend.model.classroom.ClassroomTest;
import org.ubp.ent.backend.model.classroom.equipement.RoomEquipmentTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 22/11/2015.
 */
public class ClassroomDomainTest {

    public static ClassroomDomain createOne(String name, int equipmentCount) {
        Classroom classroom = ClassroomTest.createOne(name);

        for (int i = 0; i < equipmentCount; i++) {
            classroom.addEquipment(RoomEquipmentTest.createOne("equipment " + i));
        }
        return new ClassroomDomain(classroom);
    }
    public static ClassroomDomain createOne(int equipmentCount) {
        return createOne("Default classroom name", equipmentCount);
    }
    public static ClassroomDomain createOne(String name) {
        return createOne(name, 0);
    }

    public static ClassroomDomain createOne() {
        return createOne(0);
    }

    @Test
    public void shouldNotInstantiateWithNullClassroom() {
        try {
            new ClassroomDomain(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldCreateDomainFromModel() {
        Classroom model = ClassroomTest.createOne();
        model.setId(12L);
        ClassroomDomain domain = new ClassroomDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        assertThat(domain.getRoomCapacity()).isEqualTo(model.getRoomCapacity().getMaxCapacity());
        // Model to domain should not transform equipments
        assertThat(domain.getEquipments()).hasSize(0);
    }

    @Test
    public void shouldTransformDomainToModel() {
        Classroom model = ClassroomTest.createOne();
        model.setId(12L);
        ClassroomDomain domain = new ClassroomDomain(model);
        domain.setId(12L);

        Classroom domainToModel = domain.toModel();
        assertThat(domainToModel.getId()).isEqualTo(model.getId());
        assertThat(domainToModel.getName()).isEqualTo(model.getName());
        assertThat(domainToModel.getRoomCapacity()).isEqualTo(model.getRoomCapacity());
    }

    @Test
    public void shouldPopulateListOfEquipmentWhenCreatingDomainFromModel() {
        Classroom model = ClassroomTest.createOne();
        model.addEquipment(RoomEquipmentTest.createOne());
        ClassroomDomain domain = new ClassroomDomain(model);

        assertThat(domain.getEquipments()).hasSameSizeAs(domain.getEquipments());
    }

    @Test
    public void shouldPopulateListOfEquipmentWhenTransformingDomainFromModel() {
        Classroom model = ClassroomTest.createOne();
        ClassroomDomain domain = new ClassroomDomain(model);
        domain.getEquipments().add(new RoomEquipmentDomain(RoomEquipmentTest.createOne(), domain));

        Classroom domainToModel = domain.toModel();
        assertThat(domainToModel.getEquipments()).hasSameSizeAs(domain.getEquipments());
    }

}