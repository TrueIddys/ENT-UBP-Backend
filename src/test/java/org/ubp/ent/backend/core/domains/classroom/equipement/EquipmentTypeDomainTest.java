package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 21/11/2015.
 */
public class EquipmentTypeDomainTest {

    public static EquipmentTypeDomain createOne(String equipmentName) {
        return new EquipmentTypeDomain(EquipmentTypeTest.createOne(equipmentName));
    }

    public static EquipmentTypeDomain createOne() {
        return createOne("Default equipment type name.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullEquipmentType() {
        new EquipmentTypeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("equipment name");
        equipmentType.setId(12L);
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        assertThat(domain.getId()).isEqualTo(equipmentType.getId());
        assertThat(domain.getName()).isEqualTo(equipmentType.getName());
    }

    @Test
    public void shouldTransformToModel() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("equipment name");
        equipmentType.setId(12L);
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);
        domain.setId(12L);

        assertThat(domain.toModel()).isEqualTo(equipmentType);
    }
}