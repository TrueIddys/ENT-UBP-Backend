package org.ubp.ent.backend.core.domains.course;

import org.junit.Test;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 15/02/2016.
 */
public class CourseDomainTest {

    public static CourseDomain createOne(String name) {
        return new CourseDomain(CourseTest.createOne(name));
    }

    public static CourseDomain createOne()
    {
        return new CourseDomain(CourseTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new CourseDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Course model = CourseTest.createOne();
        model.setId(12L);
        CourseDomain domain = new CourseDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        assertThat(domain.getType()).isEqualTo(model.getType());
    }

    @Test
    public void shouldTransformToModel() {
        Course model = CourseTest.createOne();
        model.setId(12L);
        CourseDomain domain = new CourseDomain(model);

        Course newModel = domain.toModel();
        assertThat(newModel.getId()).isEqualTo(model.getId());
        assertThat(newModel.getName()).isEqualTo(model.getName());
        assertThat(newModel.getType()).isEqualTo(model.getType());

    }

}
