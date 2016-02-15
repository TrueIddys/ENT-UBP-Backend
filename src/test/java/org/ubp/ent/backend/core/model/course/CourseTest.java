package org.ubp.ent.backend.core.model.course;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Maxime on 15/02/2016.
 */
public class CourseTest {

    public static Course createOne(String name) {
        return new Course(name, ClassroomType.CM);
    }

    public static Course createOne() {
        return new Course(createValidName(), ClassroomType.CM);
    }

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(9, 15);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }


    @Test
    public void shouldInstantiate() {
        Course course = createOne();

        assertThat(course.getName()).isNotNull();
        assertThat(course.getType()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Course(null, ClassroomType.CM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new Course(" ", ClassroomType.CM);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullClassroomType() {
        new Course(createValidName(), null);

    }

}
