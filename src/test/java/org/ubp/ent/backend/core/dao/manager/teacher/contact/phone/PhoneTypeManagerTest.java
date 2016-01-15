package org.ubp.ent.backend.core.dao.manager.teacher.contact.phone;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.PhoneTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 15/01/2016.
 */
public class PhoneTypeManagerTest extends WebApplicationTest {

    @Inject
    private PhoneTypeManager manager;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNullId() {
        manager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        PhoneType model = PhoneTypeTest.createOne();
        model.setId(12L);

        manager.create(model);
    }

    @Test
    public void shouldCreate() {
        PhoneType model = PhoneTypeTest.createOne();
        PhoneType saved = manager.create(model);

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(manager.findAll()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNullId() {
        manager.findOneById(null);
    }

    @Test(expected = PhoneTypeResourceNotFoundException.class)
    public void shouldFailFindOneByIdNonExisting() {
        manager.findOneById(135L);
    }

    @Test
    public void shouldFindOneById() {
        PhoneType model = PhoneTypeTest.createOne();
        model = manager.create(model);

        assertThat(manager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test
    public void shouldFindAll() {
        for (int i = 0; i < 3; ++i) {
            manager.create(PhoneTypeTest.createOne());
        }
        assertThat(manager.findAll()).hasSize(3);
    }

}
