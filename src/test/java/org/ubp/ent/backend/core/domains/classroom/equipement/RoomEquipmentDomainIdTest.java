package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomainTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 22/11/2015.
 */
public class RoomEquipmentDomainIdTest {

    @Test
    public void shouldInstantiate() {
        RoomEquipmentDomainId id = new RoomEquipmentDomainId(ClassroomDomainTest.createOne("Salle SCLV"), EquipmentTypeDomainTest.createOne("Pc de bureau"));

        assertThat(id.getClassroom().getName()).isEqualTo("Salle SCLV");
        assertThat(id.getEquipmentType().getName()).isEqualTo("Pc de bureau");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithANullClassroom() {
        new RoomEquipmentDomainId(null, EquipmentTypeDomainTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithANullEquipmentType() {
        new RoomEquipmentDomainId(ClassroomDomainTest.createOne(), null);
    }

    @Test
    public void shouldBeEqualWithSameClassroomAndEquipmentType() {
        ClassroomDomain classroomDomainTest = ClassroomDomainTest.createOne();
        EquipmentTypeDomain equipmentTypeDomainTest = EquipmentTypeDomainTest.createOne();

        RoomEquipmentDomainId one = new RoomEquipmentDomainId(classroomDomainTest, equipmentTypeDomainTest);
        RoomEquipmentDomainId two = new RoomEquipmentDomainId(classroomDomainTest, equipmentTypeDomainTest);

        assertThat(one).isEqualTo(two);
    }

}