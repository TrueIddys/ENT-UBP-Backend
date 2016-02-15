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
        Course course = CourseTest.createOne(name);
        CourseDomain domain = new CourseDomain(course);

        return domain;
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

    @Test
    public void shouldBeEqualByName() {
        CourseDomain domain = CourseDomainTest.createOne("SL5");
        CourseDomain domain2 = CourseDomainTest.createOne("SL5");

        assertThat(domain2).isEqualTo(domain);
    }

    @Test
    public void shouldNotBeEqualWithDifferentName() {
        CourseDomain domain = CourseDomainTest.createOne("SL5");
        CourseDomain domain2 = CourseDomainTest.createOne("SL6");

        assertThat(domain2).isNotEqualTo(domain);
    }
}
