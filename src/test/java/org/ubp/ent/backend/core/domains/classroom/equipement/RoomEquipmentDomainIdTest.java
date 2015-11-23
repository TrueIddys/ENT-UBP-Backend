package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomainTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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

    @Test
    public void shouldNotInstantiateWithANullClassroom() {
        try {
            new RoomEquipmentDomainId(null, EquipmentTypeDomainTest.createOne());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithANullEquipmentType() {
        try {
            new RoomEquipmentDomainId(ClassroomDomainTest.createOne(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
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