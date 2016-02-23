package org.ubp.ent.backend.core.dao.manager.wish;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.exceptions.database.CourseAlreadyAssignedToAnotherWish;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.WishResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.wish.Wish;
import org.ubp.ent.backend.core.model.wish.WishState;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.ubp.ent.backend.core.model.wish.WishState.PENDING;

/**
 * Created by Anthony on 23/02/2016.
 */
public class WishManagerTest extends WebApplicationTest {

    @Inject
    private TestScenarioHelper helper;

    @Inject
    private WishManager wishManager;

    @Test
    public void shouldFindAll() {
        wishManager.create(helper.createCourse().getId(), helper.createEmptyTeacher().getId());
        wishManager.create(helper.createCourse().getId(), helper.createEmptyTeacher().getId());

        assertThat(wishManager.findAll()).hasSize(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindAllForCourseWithNullId() {
        wishManager.findAllForCourse(null);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailFindAllForCourseWithNonExisting() {
        wishManager.findAllForCourse(12L);
    }

    @Test
    public void shouldFindAllForCourse() {
        Course course1 = helper.createCourse();
        Course course2 = helper.createCourse();
        Teacher teacher = helper.createEmptyTeacher();

        wishManager.create(course1.getId(), teacher.getId());
        wishManager.create(course1.getId(), helper.createEmptyTeacher().getId());
        wishManager.create(course1.getId(), helper.createEmptyTeacher().getId());

        wishManager.create(course2.getId(), teacher.getId());
        wishManager.create(course2.getId(), helper.createEmptyTeacher().getId());

        assertThat(wishManager.findAllForCourse(course1.getId())).hasSize(3);
        assertThat(wishManager.findAllForCourse(course2.getId())).hasSize(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindAllForTeacherWithNullId() {
        wishManager.findAllForTeacher(null);

    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailFindAllForTeacherWithNonExisting() {
        wishManager.findAllForTeacher(12L);
    }

    @Test
    public void shouldFindAllForTeacher() {
        Teacher teacher1 = helper.createEmptyTeacher();
        Teacher teacher2 = helper.createEmptyTeacher();
        Course course = helper.createCourse();

        wishManager.create(course.getId(), teacher1.getId());
        wishManager.create(helper.createCourse().getId(), teacher1.getId());
        wishManager.create(helper.createCourse().getId(), teacher1.getId());

        wishManager.create(course.getId(), teacher2.getId());
        wishManager.create(helper.createCourse().getId(), teacher2.getId());

        assertThat(wishManager.findAllForTeacher(teacher1.getId())).hasSize(3);
        assertThat(wishManager.findAllForTeacher(teacher2.getId())).hasSize(2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneWithNullCourseId() {
        wishManager.create(null, helper.createEmptyTeacher().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneWithNullTeacherId() {
        wishManager.create(helper.createCourse().getId(), null);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailFindOneWithNonExistingCourse() {
        wishManager.create(22L, helper.createEmptyTeacher().getId());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailFindOneWithNonExistingTeacher() {
        wishManager.create(helper.createCourse().getId(), 22L);
    }

    @Test(expected = WishResourceNotFoundException.class)
    public void shouldFailFindOneWithNonPersistedWish() {
        wishManager.findOneForCourseAndTeacher(helper.createCourse().getId(), helper.createEmptyTeacher().getId());
    }

    @Test
    public void shouldFindOne() {
        Course course = helper.createCourse();
        Teacher teacher = helper.createTeacher();

        wishManager.create(course.getId(), teacher.getId());
        Wish fetched = wishManager.findOneForCourseAndTeacher(course.getId(), teacher.getId());
        assertThat(fetched.getCourse()).isEqualTo(course);
        assertThat(fetched.getTeacher()).isEqualTo(teacher);
    }

    @Test
    public void shouldCreate() {
        Course course = helper.createCourse();
        Teacher teacher = helper.createEmptyTeacher();

        wishManager.create(course.getId(), teacher.getId());

        List<Wish> wishes = wishManager.findAll();
        assertThat(wishes).hasSize(1);
        assertThat(wishes.get(0).getState()).isEqualTo(PENDING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNullCourseId() {
        wishManager.create(null, helper.createEmptyTeacher().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNullTeacherId() {
        wishManager.create(helper.createCourse().getId(), null);
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailCreateWithNonExistingCourse() {
        wishManager.create(22L, helper.createEmptyTeacher().getId());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailCreateWithNonExistingTeacher() {
        wishManager.create(helper.createCourse().getId(), 22L);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFailCreateWishTwiceForSameCourseAndTeacher() {
        Course course = helper.createCourse();
        Teacher teacher = helper.createTeacher();

        wishManager.create(course.getId(), teacher.getId());
        wishManager.create(course.getId(), teacher.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAcceptWishWithNullCourseId() {
        wishManager.acceptWish(null, helper.createEmptyTeacher().getId());
    }

    @Test(expected = CourseResourceNotFoundException.class)
    public void shouldFailAcceptWishWithNonExistingCourse() {
        wishManager.acceptWish(25L, helper.createEmptyTeacher().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAcceptWishWithNullTeacherId() {
        wishManager.acceptWish(helper.createCourse().getId(), null);
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAcceptWishWithNonExistingTeacher() {
        wishManager.acceptWish(helper.createCourse().getId(), 25L);
    }

    @Test(expected = WishResourceNotFoundException.class)
    public void shouldFailAcceptWishIfWishDoesNotExists() {
        wishManager.acceptWish(helper.createCourse().getId(), helper.createEmptyTeacher().getId());
    }

    @Test(expected = CourseAlreadyAssignedToAnotherWish.class)
    public void shouldFailAcceptWishIfAnotherIsAlreadyAccepterForThisCourse() {
        Course course = helper.createCourse();
        Teacher teacher1 = helper.createEmptyTeacher();
        Teacher teacher2 = helper.createEmptyTeacher();

        Wish wish1 = wishManager.create(course.getId(), teacher1.getId());
        Wish wish2 = wishManager.create(course.getId(), teacher2.getId());

        wishManager.acceptWish(wish1.getCourse().getId(), wish1.getTeacher().getId());
        wishManager.acceptWish(wish2.getCourse().getId(), wish2.getTeacher().getId());
    }

    @Test
    public void shouldAcceptWish() {
        Course course = helper.createCourse();
        Teacher teacher = helper.createEmptyTeacher();

        Wish wish = wishManager.create(course.getId(), teacher.getId());

        wish = wishManager.acceptWish(wish.getCourse().getId(), wish.getTeacher().getId());
        assertThat(wish.getState()).isEqualTo(WishState.ACCEPTED);
    }

    @Test
    public void shouldDenyAllOtherWishesOnSameCourseWhenAcceptingWish() {
        Course courseA = helper.createCourse();
        Course courseB = helper.createCourse();
        List<Teacher> teachers = Arrays.asList(helper.createEmptyTeacher(), helper.createEmptyTeacher(), helper.createEmptyTeacher());

        // create wish for each teacher and for each courses
        for (Teacher teacher : teachers) {
            wishManager.create(courseA.getId(), teacher.getId());
            wishManager.create(courseB.getId(), teacher.getId());
        }
        // accept one.
        wishManager.acceptWish(courseA.getId(), teachers.get(0).getId());

        List<Wish> wishes = wishManager.findAllForCourse(courseA.getId());
        assertThat(wishes).isNotEmpty();

        List<WishState> wishStates = wishes.stream().map(Wish::getState).collect(Collectors.toList());
        long accepted = wishStates.stream().filter(s -> s.equals(WishState.ACCEPTED)).count();
        long denied = wishStates.stream().filter(s -> s.equals(WishState.DENIED)).count();

        assertThat(accepted).isEqualTo(1);
        assertThat(denied).isEqualTo(2);
    }

    @Test
    public void shouldNotDenyAllOtherWishesOnDifferentCoursesWhenAcceptingWish () {
        Course courseA = helper.createCourse();
        Course courseB = helper.createCourse();
        List<Teacher> teachers = Arrays.asList(helper.createEmptyTeacher(), helper.createEmptyTeacher(), helper.createEmptyTeacher());

        // create wish for each teacher and for each courses
        for (Teacher teacher : teachers) {
            wishManager.create(courseA.getId(), teacher.getId());
            wishManager.create(courseB.getId(), teacher.getId());
        }
        // accept one.
        wishManager.acceptWish(courseA.getId(), teachers.get(0).getId());

        List<Wish> wishes = wishManager.findAllForCourse(courseB.getId());
        assertThat(wishes).isNotEmpty();

        List<WishState> wishStates = wishes.stream().map(Wish::getState).collect(Collectors.toList());
        long pending = wishStates.stream().filter(s -> s.equals(WishState.PENDING)).count();

        assertThat(pending).isEqualTo(3);
    }

}
