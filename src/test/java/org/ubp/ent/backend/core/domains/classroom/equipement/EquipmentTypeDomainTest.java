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
    public void shouldNotInstantiateWithNullModel() {
        new EquipmentTypeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        EquipmentType model = EquipmentTypeTest.createOne("equipment name");
        model.setId(12L);
        EquipmentTypeDomain domain = new EquipmentTypeDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldTransformToModel() {
        EquipmentType model = EquipmentTypeTest.createOne("equipment name");
        model.setId(12L);
        EquipmentTypeDomain domain = new EquipmentTypeDomain(model);
        domain.setId(12L);

        assertThat(domain.toModel()).isEqualTo(model);
    }
}