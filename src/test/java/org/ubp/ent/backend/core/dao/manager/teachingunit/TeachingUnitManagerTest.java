package org.ubp.ent.backend.core.dao.manager.teachingunit;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeachingUnitResourceNotFoundException;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.module.ModuleTest;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnitTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnitManagerTest extends WebApplicationTest {

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
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        teachingUnitManager.create(model);

        assertThat(teachingUnitManager.findAll()).hasSize(1);

        TeachingUnit model2 = TeachingUnitTest.createOne("Technologies du web");
        teachingUnitManager.create(model2);

        assertThat(teachingUnitManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReferenceWhenCreating() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");

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

    @Test
    public void shouldNotCreateModuleInTeachingUnitOnCascade() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        model.addModule(new Module("Anglais"));
        teachingUnitManager.create(model);

        TeachingUnit modelDB = teachingUnitManager.findAll().get(0);

        assertThat(modelDB.getModules()).hasSize(0);
    }

    @Test
    public void shouldAddModuleToTeachingUnit() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        model = teachingUnitManager.create(model);

        Module module = ModuleTest.createOne();

        teachingUnitManager.addModule(model.getId(), module);

        TeachingUnit fetched = teachingUnitManager.findOneByIdJoiningModules(model.getId());
        assertThat(fetched.getModules()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddModuleToteachingUnitIfModuleIsNull() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        model = teachingUnitManager.create(model);

        teachingUnitManager.addModule(model.getId(), null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddModuleIfTeachingUnitIdIsNull() {
        Module module = ModuleTest.createOne();

        teachingUnitManager.addModule(null, module);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldNotAddModuleWithNoneExistingTeachingUnit() {
        teachingUnitManager.addModule(12L, ModuleTest.createOne());
    }

    @Test
    public void shouldFindOneByIdJoiningModules() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        model = teachingUnitManager.create(model);

        Module module = ModuleTest.createOne();

        module = teachingUnitManager.addModule(model.getId(), module);

        TeachingUnit fetched = teachingUnitManager.findOneByIdJoiningModules(model.getId());
        assertThat(fetched).isEqualTo(model);
        assertThat(fetched.getModules().get(0)).isEqualTo(module);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdJoiningModulesWithNull() {
        teachingUnitManager.findOneByIdJoiningModules(null);
    }

    @Test(expected = TeachingUnitResourceNotFoundException.class)
    public void shouldFailFindOneByIdJoiningModulesWithNonExistingId() {
        teachingUnitManager.findOneByIdJoiningModules(205L);
    }


    @Test
    public void shouldFindAllJoiningModules() {
        TeachingUnit model = TeachingUnitTest.createOne("Technique de communication, anglais");
        model = teachingUnitManager.create(model);

        Module module = ModuleTest.createOne();

        module = teachingUnitManager.addModule(model.getId(), module);

        List<TeachingUnit> fetched = teachingUnitManager.findAllJoiningModules();
        assertThat(fetched.get(0)).isEqualTo(model);
        assertThat(fetched.get(0).getModules().get(0)).isEqualTo(module);
    }
}
