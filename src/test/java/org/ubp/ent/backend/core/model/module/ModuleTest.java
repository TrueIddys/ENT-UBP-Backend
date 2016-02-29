package org.ubp.ent.backend.core.model.module;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 28/02/2016.
 */
public class ModuleTest {

    public static Module createOne(String name) {
        Module module = createOneEmpty(name);
        module.addCourse(CourseTest.createOne());
        module.addCourse(CourseTest.createOne());

        return module;
    }

    public static Module createOneEmpty(String name) {
        return new Module(name);
    }

    public static Module createOne() {
        return createOne(createValidName());
    }

    public static Module createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(9, 15);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }


    @Test
    public void shouldInstantiate() {
        Module module = createOneEmpty();

        assertThat(module.getId()).isNull();
        assertThat(module.getName()).isNotNull();
        assertThat(module.getCourses()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new Module(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Module(" ");
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenAddingANullCourse() {
        Module module = createOne();
        module.addCourse(null);
    }

    @Test
    public void shouldAddCourseToSet() {
        Course course = CourseTest.createOne();

        Module module = createOneEmpty();
        module.addCourse(course);

        assertThat(module.getCourses()).containsOnly(course);
    }

    @Test
    public void shouldBeEqualById() {
        Module first = ModuleTest.createOne();
        first.setId(1L);
        Module second = ModuleTest.createOne();
        second.setId(1L);

        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        Module first = ModuleTest.createOne();
        first.setId(1L);
        Module second = ModuleTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        Module first = ModuleTest.createOne();
        Module second = ModuleTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }


}
