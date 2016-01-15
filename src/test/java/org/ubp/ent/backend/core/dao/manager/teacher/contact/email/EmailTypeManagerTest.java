package org.ubp.ent.backend.core.dao.manager.teacher.contact.email;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EmailTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTypeTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 15/01/2016.
 */
public class EmailTypeManagerTest extends WebApplicationTest {

    @Inject
    private EmailTypeManager manager;

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNullId() {
        manager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        EmailType model = EmailTypeTest.createOne();
        model.setId(12L);

        manager.create(model);
    }

    @Test
    public void shouldCreate() {
        EmailType model = EmailTypeTest.createOne();
        EmailType saved = manager.create(model);

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(manager.findAll()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNullId() {
        manager.findOneById(null);
    }

    @Test(expected = EmailTypeResourceNotFoundException.class)
    public void shouldFailFindOneByIdNonExisting() {
        manager.findOneById(135L);
    }

    @Test
    public void shouldFindOneById() {
        EmailType model = EmailTypeTest.createOne();
        model = manager.create(model);

        assertThat(manager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test
    public void shouldFindAll() {
        for (int i = 0; i < 3; ++i) {
            manager.create(EmailTypeTest.createOne());
        }
        assertThat(manager.findAll()).hasSize(3);
    }

}
