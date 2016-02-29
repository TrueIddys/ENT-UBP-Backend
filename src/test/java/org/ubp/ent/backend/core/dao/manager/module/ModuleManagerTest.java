package org.ubp.ent.backend.core.dao.manager.module;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ModuleResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.module.ModuleTest;
import org.ubp.ent.backend.core.model.type.ClassroomType;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class ModuleManagerTest extends WebApplicationTest {

    @Inject
    private ModuleManager moduleManager;

    @Test
    public void shouldFindOneById() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        model = moduleManager.create(model);

        assertThat(moduleManager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        moduleManager.findOneById(null);
    }

    @Test(expected = ModuleResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExistingId() {
        moduleManager.findOneById(205L);
    }

    @Test
    public void shouldFindOneByIdJoiningCourses() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        model = moduleManager.create(model);

        Course course = CourseTest.createOne();

        course = moduleManager.addCourse(model.getId(), course);

        Module fetched = moduleManager.findOneByIdJoiningCourses(model.getId());
        assertThat(fetched).isEqualTo(model);
        assertThat(fetched.getCourses().get(0)).isEqualTo(course);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdJoiningCoursesWithNull() {
        moduleManager.findOneByIdJoiningCourses(null);
    }

    @Test(expected = ModuleResourceNotFoundException.class)
    public void shouldFailFindOneByIdJoiningCoursesWithNonExistingId() {
        moduleManager.findOneByIdJoiningCourses(205L);
    }


    @Test
    public void shouldFindAllJoiningCourses() {
        Module model = ModuleTest.createOneEmpty("Genie log");
        model = moduleManager.create(model);

        Course course = CourseTest.createOne();

        course = moduleManager.addCourse(model.getId(), course);

        List<Module> fetched = moduleManager.findAllJoiningCourses();
        assertThat(fetched.get(0)).isEqualTo(model);
        assertThat(fetched.get(0).getCourses().get(0)).isEqualTo(course);
    }

    @Test
    public void shouldCreate() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        moduleManager.create(model);

        assertThat(moduleManager.findAll()).hasSize(1);

        Module model2 = ModuleTest.createOneEmpty("Compilation");
        moduleManager.create(model2);

        assertThat(moduleManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReferenceWhenCreating() {
        Module model = ModuleTest.createOneEmpty("Genie Log");

        moduleManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFailCreateTwoModuleWithTheSameName() {
        String name = "Non-Unique-Name";
        Module model = ModuleTest.createOneEmpty(name);
        moduleManager.create(model);

        Module model2 = ModuleTest.createOneEmpty(name);
        moduleManager.create(model2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        moduleManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsDefined() {
        Module model = ModuleTest.createOneEmpty();
        model.setId(25L);

        moduleManager.create(model);
    }

    @Test
    public void shouldNotCreateCourseInModuleOnCascade() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        model.addCourse(new Course("Genie Log", ClassroomType.CM));
        moduleManager.create(model);

        Module modelDB = moduleManager.findAll().get(0);

        assertThat(modelDB.getCourses()).hasSize(0);
    }

    @Test
    public void shouldAddCourseToModule() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        model = moduleManager.create(model);

        Course course = CourseTest.createOne();

        moduleManager.addCourse(model.getId(), course);

        Module fetched = moduleManager.findOneByIdJoiningCourses(model.getId());
        assertThat(fetched.getCourses()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddCourseToModuleIfCourseIsNull() {
        Module model = ModuleTest.createOneEmpty("Genie Log");
        model = moduleManager.create(model);

        moduleManager.addCourse(model.getId(), null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddCourseIfModuleIdIsNull() {
        Course course = CourseTest.createOne();

        moduleManager.addCourse(null, course);
    }

    @Test(expected = ModuleResourceNotFoundException.class)
    public void shouldNotAddCourseWithNoneExistingModule() {
        moduleManager.addCourse(12L, CourseTest.createOne());
    }
}
