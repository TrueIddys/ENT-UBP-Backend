package org.ubp.ent.backend.core.model.formation;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.student.Student;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationLeafTest {

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(6, 12);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }

    public static FormationLeaf createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    public static FormationLeaf createOneEmpty(String name) {
        return new FormationLeaf(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new FormationLeaf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new FormationLeaf(" ");
    }

    @Test
    public void shouldInstantiate() {
        FormationLeaf model = new FormationLeaf("Master 1 informatique");

        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Master 1 informatique");
        assertThat(model.getCourses()).isEmpty();
        assertThat(model.getStudents()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddStudentWithNull() {
        FormationLeaf model = createOneEmpty();
        model.addStudent(null);
    }

    @Test
    public void shouldAddStudent() {
        FormationLeaf model = createOneEmpty();
        model.addStudent(new Student());
        model.addStudent(new Student());

        assertThat(model.getStudents()).hasSize(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddCourseWithNull() {
        FormationLeaf model = createOneEmpty();
        model.addCourse(null);
    }

    @Test
    public void shouldAddCourse() {
        FormationLeaf model = createOneEmpty();
        model.addCourse(CourseTest.createOne());
        model.addCourse(CourseTest.createOne());

        assertThat(model.getCourses()).hasSize(2);
    }

    @Test
    public void shouldReturnTrueIsLeaf() {
        assertThat(createOneEmpty().isLeaf()).isTrue();
    }

}
