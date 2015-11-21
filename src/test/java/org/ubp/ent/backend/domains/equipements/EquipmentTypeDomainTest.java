package org.ubp.ent.backend.domains.equipements;

import org.junit.Test;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentTypeTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/11/2015.
 */
public class EquipmentTypeDomainTest {

    @Test
    public void shouldNotInstantiateWithNullEquipmentType() {
        try {
            new EquipmentTypeDomain(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldCreateFromModel() {
        EquipmentType equipmentType = EquipmentTypeTest.createValidEquipmentType("equipment name");
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        assertThat(domain.getName()).isEqualTo(equipmentType.getName());
    }

    @Test
    public void shouldTransformToModel() {
        EquipmentType equipmentType = EquipmentTypeTest.createValidEquipmentType("equipment name");
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        assertThat(domain.toModel()).isEqualTo(equipmentType);
    }

}