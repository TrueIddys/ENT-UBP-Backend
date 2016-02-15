package org.ubp.ent.backend.core.dao.manager.course;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.dao.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ClassroomResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
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
        Course model = CourseTest.createOne("Anglais");
        model = courseManager.create(model);

        Course fetched = courseManager.findOneById(model.getId());
        assertThat(fetched.getId()).isEqualTo(model.getId());
        assertThat(fetched.getName()).isEqualTo(model.getName());
        assertThat(fetched.getType()).isEqualTo(model.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        courseManager.findOneById(null);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExistingId() {
        courseManager.findOneById(205L);
    }


    @Test
    public void shouldCreate() {
        Course model = CourseTest.createOne("Espagnol");
        courseManager.create(model);

        assertThat(courseManager.findAll()).hasSize(1);

        Course model2 = CourseTest.createOne("Allemand");
        courseManager.create(model2);

        assertThat(courseManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReferenceWhenCreating() {
        Course model = CourseTest.createOne("Allemand");

        courseManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        courseManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsDefined() {
        Course model = CourseTest.createOne();
        model.setId(25L);

        courseManager.create(model);
    }

}
