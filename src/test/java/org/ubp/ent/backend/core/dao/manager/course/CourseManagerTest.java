package org.ubp.ent.backend.core.dao.manager.course;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.dao.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ClassroomResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EquipmentTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 15/02/2016.
 */
public class CourseManagerTest extends WebApplicationTest{

    @Inject
    private CourseManager courseManager;

    @Test
    public void shouldFindOneById() {
        Course model = CourseTest.createOneEmpty("SCI_3006");
        model = courseManager.create(model);

        assertThat(courseManager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        courseManager.findOneById(null);
    }

    @Test(expected = ClassroomResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExistingId() {
        courseManager.findOneById(205L);
    }


    @Test
    public void shouldCreate() {
        Course model = CourseTest.createOneEmpty("SL4");
        courseManager.create(model);

        assertThat(courseManager.findAll()).hasSize(1);

        Course model2 = CourseTest.createOneEmpty("SL5");
        courseManager.create(model2);

        assertThat(courseManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReferenceWhenCreating() {
        Course model = CourseTest.createOneEmpty("SL5");

        courseManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFailCreateTwoCourseWithTheSameName() {
        String name = "Non-Unique-Name";
        Course model = CourseTest.createOneEmpty(name);
        courseManager.create(model);

        Course model2 = CourseTest.createOneEmpty(name);
        courseManager.create(model2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        courseManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsDefined() {
        Course model = CourseTest.createOneEmpty();
        model.setId(25L);

        courseManager.create(model);
    }

}
