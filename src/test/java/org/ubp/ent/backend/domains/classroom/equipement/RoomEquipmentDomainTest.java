package org.ubp.ent.backend.domains.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.domains.classroom.ClassroomDomainTest;
import org.ubp.ent.backend.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.model.classroom.equipement.RoomEquipmentTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 22/11/2015.
 */
public class RoomEquipmentDomainTest {


    public static RoomEquipmentDomain createOne(String name, ClassroomDomain classroomDomain) {
        return new RoomEquipmentDomain(RoomEquipmentTest.createOne(), classroomDomain);
    }

    public static RoomEquipmentDomain createOne(ClassroomDomain classroomDomain) {
        return createOne("default equipment name", classroomDomain);
    }

    @Test
    public void shouldNotInstantiateWithoutRoomEquipment() {
        try {
            RoomEquipmentDomain domain = new RoomEquipmentDomain(null, ClassroomDomainTest.createOne());
            fail();
        }catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithoutClassroomDomain() {
        try {
            RoomEquipmentDomain domain = new RoomEquipmentDomain(RoomEquipmentTest.createOne(), null);
            fail();
        }catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldCreateDomainFromModel() {
        RoomEquipment model = RoomEquipmentTest.createOne();
        ClassroomDomain classroomDomain = ClassroomDomainTest.createOne();
        RoomEquipmentDomain domain = new RoomEquipmentDomain(model, classroomDomain);


        assertThat(domain.getClassroom()).isEqualTo(classroomDomain);
        assertThat(domain.getEquipmentType().getName()).isEqualTo(model.getEquipmentType().getName());
        assertThat(domain.getQuantity()).isEqualTo(model.getQuantity());
    }

    @Test
    public void shouldTransformFromDomainToModel() {
        RoomEquipment model = RoomEquipmentTest.createOne();
        ClassroomDomain classroomDomain = ClassroomDomainTest.createOne();
        RoomEquipmentDomain domain = new RoomEquipmentDomain(model, classroomDomain);

        RoomEquipment domainToModel = domain.toModel();
        assertThat(domainToModel.getEquipmentType().getName()).isEqualTo(model.getEquipmentType().getName());
        assertThat(domainToModel.getQuantity()).isEqualTo(model.getQuantity());
    }

}