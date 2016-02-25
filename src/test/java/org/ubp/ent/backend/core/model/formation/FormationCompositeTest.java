package org.ubp.ent.backend.core.model.formation;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.student.Student;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationCompositeTest {

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(6, 12);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }

    public static FormationComposite createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    public static FormationComposite createOneEmpty(String name) {
        return new FormationComposite(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new FormationComposite(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new FormationComposite(" ");
    }

    @Test
    public void shouldInstantiate() {
        FormationComposite model = new FormationComposite("Master");

        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Master");
        assertThat(model.getCourses()).isEmpty();
        assertThat(model.getStudents()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddFormationWithNull() {
        FormationComposite model = createOneEmpty();
        model.addFormation(null);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationLeafIfAlreadyContainsAFormationComposite() {
        FormationComposite model = createOneEmpty();
        model.addFormation(createOneEmpty());
        model.addFormation(FormationLeafTest.createOneEmpty());
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationCompositeIfAlreadyContainsAFormationLeaf() {
        FormationComposite model = createOneEmpty();
        model.addFormation(FormationLeafTest.createOneEmpty());
        model.addFormation(createOneEmpty());
    }

    @Test
    public void shouldAddFormation() {
        FormationComposite model = createOneEmpty();
        FormationLeaf leaf1 = FormationLeafTest.createOneEmpty();
        leaf1.addCourse(CourseTest.createOne());
        leaf1.addCourse(CourseTest.createOne());
        leaf1.addStudent(new Student());
        FormationLeaf leaf2 = FormationLeafTest.createOneEmpty();
        leaf2.addCourse(CourseTest.createOne());
        leaf1.addStudent(new Student());

        model.addFormation(leaf1);
        model.addFormation(leaf2);

        assertThat(model.getCourses()).hasSize(leaf1.getCourses().size() + leaf2.getCourses().size());
        assertThat(model.getStudents()).hasSize(leaf1.getStudents().size() + leaf2.getStudents().size());
    }

    @Test
    public void shouldReturnFalseIsLeaf() {
        assertThat(createOneEmpty().isLeaf()).isFalse();
    }

}
