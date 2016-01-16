package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomainTest;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipmentTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 22/11/2015.
 */
public class RoomEquipmentDomainTest {

    public static RoomEquipmentDomain createOne(String name, ClassroomDomain classroomDomain) {
        return new RoomEquipmentDomain(RoomEquipmentTest.createOne(name), classroomDomain);
    }

    public static RoomEquipmentDomain createOne(ClassroomDomain classroomDomain) {
        return createOne("default equipment name", classroomDomain);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullRoomEquipment() {
        new RoomEquipmentDomain(null, ClassroomDomainTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullClassroomDomain() {
        new RoomEquipmentDomain(RoomEquipmentTest.createOne(), null);
    }

    @Test
    public void shouldCreateFromModel() {
        RoomEquipment model = RoomEquipmentTest.createOne();
        ClassroomDomain classroomDomain = ClassroomDomainTest.createOne();
        RoomEquipmentDomain domain = new RoomEquipmentDomain(model, classroomDomain);

        assertThat(domain.getClassroom()).isEqualTo(classroomDomain);
        assertThat(domain.getEquipmentType().getName()).isEqualTo(model.getEquipmentType().getName());
        assertThat(domain.getQuantity()).isEqualTo(model.getQuantity().getMaxQuantity());
    }

    @Test
    public void shouldTransformToModel() {
        RoomEquipment model = RoomEquipmentTest.createOne();
        ClassroomDomain classroomDomain = ClassroomDomainTest.createOne();
        RoomEquipmentDomain domain = new RoomEquipmentDomain(model, classroomDomain);

        RoomEquipment domainToModel = domain.toModel();
        assertThat(domainToModel.getEquipmentType().getName()).isEqualTo(model.getEquipmentType().getName());
        assertThat(domainToModel.getQuantity().getMaxQuantity()).isEqualTo(model.getQuantity().getMaxQuantity());
    }

}
