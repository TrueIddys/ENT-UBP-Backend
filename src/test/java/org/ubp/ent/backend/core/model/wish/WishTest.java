package org.ubp.ent.backend.core.model.wish;

import org.junit.Test;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 17/02/2016.
 */
public class WishTest {

    public static Wish createOne() {
        return createOne(CourseTest.createOne(), UniversityTeacherTest.createOne());
    }

    public static Wish createOne(Course course, Teacher teacher) {
        return new Wish(course, teacher);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullCourse() {
        new Wish(null, UniversityTeacherTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTeacher() {
        new Wish(CourseTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Course course = CourseTest.createOne();
        Teacher teacher = UniversityTeacherTest.createOne();

        Wish wish = createOne(course, teacher);

        assertThat(wish.getCourse()).isSameAs(course);
        assertThat(wish.getTeacher()).isSameAs(teacher);
    }

    @Test
    public void shouldBeEqualByCourseAndTeacher() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = UniversityTeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher);

        course = CourseTest.createOne();
        course.setId(1L);
        teacher = UniversityTeacherTest.createOne();
        teacher.setId(1L);
        Wish second = createOne(course, teacher);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTeacherAndSameCourse() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = UniversityTeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher);

        teacher = UniversityTeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseAndSameTeacher() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = UniversityTeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher);

        course = CourseTest.createOne();
        course.setId(2L);
        Wish second = createOne(course, teacher);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseAndTeacher() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = UniversityTeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher);

        course = CourseTest.createOne();
        course.setId(2L);
        teacher = UniversityTeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher);

        assertThat(first).isNotEqualTo(second);
    }

}
