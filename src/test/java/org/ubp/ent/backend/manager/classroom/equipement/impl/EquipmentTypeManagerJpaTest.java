package org.ubp.ent.backend.manager.classroom.equipement.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 21/11/2015.
 */
public class EquipmentTypeManagerJpaTest extends WebApplicationTest {

    @Inject
    private EquipmentTypeManager manager;

    @Test
    public void shouldCreate() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        manager.create(equipmentType);

        assertThat(manager.findAll()).hasSize(1);

        EquipmentType equipmentType2 = EquipmentTypeTest.createOne("Another type");
        manager.create(equipmentType2);

        assertThat(manager.findAll()).hasSize(2);
    }

    @Test
    public void shouldFailCreateTwoTypesWithTheSameName() {
        try {
            EquipmentType equipmentType = EquipmentTypeTest.createOne();
            manager.create(equipmentType);

            EquipmentType equipmentType2 = EquipmentTypeTest.createOne();
            manager.create(equipmentType2);
            fail();
        } catch (DataIntegrityViolationException e) {
            assertThat(e.getCause()).isOfAnyClassIn(ConstraintViolationException.class);
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