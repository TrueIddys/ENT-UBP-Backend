package org.ubp.ent.backend.manager.classroom.equipement.impl;

import org.junit.Test;
import org.ubp.ent.backend.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/11/2015.
 */
public class EquipmentTypeManagerJpaTest extends WebApplicationTest {

    @Inject
    EquipmentTypeManager manager;

    @Test
    public void shouldCreate() {
        EquipmentType equipmentType = EquipmentTypeTest.createValidEquipmentType("Computer");
        manager.create(equipmentType);

        assertThat(manager.findAll()).hasSize(1);

        EquipmentType equipmentType2 = EquipmentTypeTest.createValidEquipmentType("Another type");
        manager.create(equipmentType2);

        assertThat(manager.findAll()).hasSize(2);
    }

    @Test
    public void shouldFailCreateTwoTypesWithTheSameName() {
        try {
            EquipmentType equipmentType = EquipmentTypeTest.createValidEquipmentType();
            manager.create(equipmentType);

            EquipmentType equipmentType2 = EquipmentTypeTest.createValidEquipmentType();
            manager.create(equipmentType2);
            fail();
        } catch (EntityExistsException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldFailCreateWithNull() {
        try {
            manager.create(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

}