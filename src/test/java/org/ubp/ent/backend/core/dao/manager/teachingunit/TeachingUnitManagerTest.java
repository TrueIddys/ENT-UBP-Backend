package org.ubp.ent.backend.core.dao.manager.teachingunit;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnitTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnitManagerTest extends WebApplicationTest{

    @Inject
    private TeachingUnitManager teachingUnitManager;

    @Test
    public void shouldFindOneById() {
        TeachingUnit model = TeachingUnitTest.createOne("technique de communication, anglais");
        model = teachingUnitManager.create(model);

        TeachingUnit fetched = teachingUnitManager.findOneById(model.getId());
        assertThat(fetched.getId()).isEqualTo(model.getId());
        assertThat(fetched.getName()).isEqualTo(model.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        teachingUnitManager.findOneById(null);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExistingId() {
        teachingUnitManager.findOneById(205L);
    }


    @Test
    public void shouldCreate() {
        TeachingUnit model = TeachingUnitTest.createOne("Espagnol");
        teachingUnitManager.create(model);

        assertThat(teachingUnitManager.findAll()).hasSize(1);

        TeachingUnit model2 = TeachingUnitTest.createOne("Allemand");
        teachingUnitManager.create(model2);

        assertThat(teachingUnitManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReferenceWhenCreating() {
        TeachingUnit model = TeachingUnitTest.createOne("Allemand");

        teachingUnitManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        teachingUnitManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsDefined() {
        TeachingUnit model = TeachingUnitTest.createOne();
        model.setId(25L);

        teachingUnitManager.create(model);
    }
}
