package org.ubp.ent.backend.core.model.wish;

import org.junit.Test;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 17/02/2016.
 */
public class WishTest {

    public static Wish createOne() {
        return createOne(CourseTest.createOne(), TeacherTest.createOne());
    }

    public static Wish createOne(Course course, Teacher teacher) {
        return createOne(course, teacher, WishState.PENDING);
    }

    public static Wish createOne(Course course, Teacher teacher, WishState state) {
        return new Wish(course, teacher, state);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullCourse() {
        new Wish(null, TeacherTest.createOne(), WishState.PENDING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullTeacher() {
        new Wish(CourseTest.createOne(), null, WishState.PENDING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullState() {
        new Wish(CourseTest.createOne(), TeacherTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Course course = CourseTest.createOne();
        Teacher teacher = TeacherTest.createOne();

        Wish wish = createOne(course, teacher, WishState.PENDING);

        assertThat(wish.getCourse()).isSameAs(course);
        assertThat(wish.getTeacher()).isSameAs(teacher);
        assertThat(wish.getState()).isEqualTo(WishState.PENDING);
    }

    @Test
    public void shouldBeEqualByCourseAndTeacherAndState() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.PENDING);

        course = CourseTest.createOne();
        course.setId(1L);
        teacher = TeacherTest.createOne();
        teacher.setId(1L);
        Wish second = createOne(course, teacher);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTeacherButSameCourseAndState() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.PENDING);

        teacher = TeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseButSameTeacherAndState() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.PENDING);

        course = CourseTest.createOne();
        course.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentStateButSameCourseAndTeacher() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.DENIED);

        course = CourseTest.createOne();
        course.setId(2L);
        teacher = TeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseAndTeacherButSameState() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.PENDING);

        course = CourseTest.createOne();
        course.setId(2L);
        teacher = TeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentStateAndTeacherButSameCourse() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.DENIED);

        teacher = TeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseAndStateButSameTeacher() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.DENIED);

        course = CourseTest.createOne();
        course.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentCourseAndTeacherAndState() {
        Course course = CourseTest.createOne();
        course.setId(1L);
        Teacher teacher = TeacherTest.createOne();
        teacher.setId(1L);

        Wish first = createOne(course, teacher, WishState.DENIED);

        course = CourseTest.createOne();
        course.setId(2L);
        teacher = TeacherTest.createOne();
        teacher.setId(2L);
        Wish second = createOne(course, teacher, WishState.PENDING);

        assertThat(first).isNotEqualTo(second);
    }

}
