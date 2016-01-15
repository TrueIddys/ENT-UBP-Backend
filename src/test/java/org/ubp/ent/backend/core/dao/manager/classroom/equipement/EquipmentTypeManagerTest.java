package org.ubp.ent.backend.core.dao.manager.classroom.equipement;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EquipmentTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 21/11/2015.
 */
public class EquipmentTypeManagerTest extends WebApplicationTest {

    @Inject
    private EquipmentTypeManager manager;


    @Test
    public void shouldFindOneById() {
        EquipmentType model = EquipmentTypeTest.createOne("Computer");
        model = manager.create(model);

        assertThat(manager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        manager.findOneById(null);
    }

    @Test(expected = EquipmentTypeResourceNotFoundException.class)
    public void shouldFailFindByNonExistingId() {
        manager.findOneById(205L);
    }

    @Test
    public void shouldCreate() {
        EquipmentType model = EquipmentTypeTest.createOne("Computer");
        manager.create(model);

        assertThat(manager.findAll()).hasSize(1);

        EquipmentType model2 = EquipmentTypeTest.createOne("Another type");
        manager.create(model2);

        assertThat(manager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReference() {
        EquipmentType model = EquipmentTypeTest.createOne("Computer");

        manager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFailCreateTwoTypesWithTheSameName() {
        EquipmentType model = EquipmentTypeTest.createOne("duplicated-name");
        manager.create(model);

        EquipmentType model2 = EquipmentTypeTest.createOne("duplicated-name");
        manager.create(model2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        manager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsDefined() {
        EquipmentType model = EquipmentTypeTest.createOne();
        model.setId(25L);

        manager.create(model);
    }

}