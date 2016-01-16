package org.ubp.ent.backend.core.dao.manager.teacher.contact.address;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.AddressTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class AddressTypeManagerTest extends WebApplicationTest {

    @Inject
    private AddressTypeManager manager;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNullId() {
        manager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        AddressType model = AddressTypeTest.createOne();
        model.setId(12L);

        manager.create(model);
    }

    @Test
    public void shouldCreate() {
        AddressType model = AddressTypeTest.createOne();
        AddressType saved = manager.create(model);

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(manager.findAll()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNullId() {
        manager.findOneById(null);
    }

    @Test(expected = AddressTypeResourceNotFoundException.class)
    public void shouldFailFindOneByIdNonExisting() {
        manager.findOneById(135L);
    }

    @Test
    public void shouldFindOneById() {
        AddressType model = AddressTypeTest.createOne();
        model = manager.create(model);

        assertThat(manager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test
    public void shouldFindAll() {
        for (int i = 0; i < 3; ++i) {
            manager.create(AddressTypeTest.createOne());
        }
        assertThat(manager.findAll()).hasSize(3);
    }

}
