package org.ubp.ent.backend.core.controllers.wish;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.CourseAlreadyAssignedToAnotherWish;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.WishResourceNotFoundException;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.wish.Wish;
import org.ubp.ent.backend.core.model.wish.WishState;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 23/02/2016.
 */
public class WishControllerTest extends WebIntegrationTest {

    private final String WISH_BASE_URL = WishController.BASE_URL;

    @Inject
    private TestScenarioHelper helper;

    @Test
    public void shouldFindAll() throws Exception {
        helper.createWish();
        helper.createWish();

        perform(get(WISH_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldFailFindAllForCourseWithNonExisting() throws Exception {
        perform(get(WISH_BASE_URL + "/course/12"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindAllForCourse() throws Exception {
        Course course1 = helper.createCourse();
        Course course2 = helper.createCourse();

        for (int i = 0; i < 5; ++i) {
            helper.createWishForCourse(course1);
        }
        for (int i = 0; i < 3; ++i) {
            helper.createWishForCourse(course2);
        }

        perform(get(WISH_BASE_URL + "/course/" + course1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

        perform(get(WISH_BASE_URL + "/course/" + course2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldFailFindAllForTeacherWithNonExisting() throws Exception {
        perform(get(WISH_BASE_URL + "/teacher/" + 12))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindAllForTeacher() throws Exception {
        Teacher teacher1 = helper.createEmptyTeacher();
        Teacher teacher2 = helper.createEmptyTeacher();

        for (int i = 0; i < 5; ++i) {
            helper.createWishForTeacher(teacher1);
        }
        for (int i = 0; i < 3; ++i) {
            helper.createWishForTeacher(teacher2);
        }

        perform(get(WISH_BASE_URL + "/teacher/" + teacher1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));

        perform(get(WISH_BASE_URL + "/teacher/" + teacher2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldFailFindOneWithNonExistingCourse() throws Exception {
        perform(get(WISH_BASE_URL + "/course/22/teacher/" + helper.createEmptyTeacher().getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailFindOneWithNonExistingTeacher() throws Exception {
        perform(get(WISH_BASE_URL + "/course/" + helper.createCourse().getId() +"/teacher/22"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailFindOneWithNonPersistedWish() throws Exception {
        perform(get(WISH_BASE_URL + "/course/" + helper.createCourse().getId() +"/teacher/" + helper.createEmptyTeacher().getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindOne() throws Exception {
        Wish model = helper.createWish();

        perform(get(WISH_BASE_URL + "/course/" + model.getCourse().getId() +"/teacher/" + model.getTeacher().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.course.id", is(model.getCourse().getId().intValue())))
                .andExpect(jsonPath("$.teacher.id", is(model.getTeacher().getId().intValue())))
                .andExpect(jsonPath("$.state", is(model.getState().toString())));
    }

    @Test
    public void shouldFailCreateWithNonExistingCourse() throws Exception {
        perform(post(WISH_BASE_URL + "/course/22/teacher/" + helper.createEmptyTeacher().getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailCreateWithNonExistingTeacher() throws Exception {
        perform(post(WISH_BASE_URL + "/course/" + helper.createCourse().getId() + "/teacher/22"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailCreateWishTwiceForSameCourseAndTeacher() throws Exception {
        Course course = helper.createCourse();
        Teacher teacher = helper.createEmptyTeacher();

        perform(post(WISH_BASE_URL + "/course/" + course.getId() + "/teacher/" + teacher.getId()))
                .andExpect(status().isCreated());
        perform(post(WISH_BASE_URL + "/course/" + course.getId() + "/teacher/" + teacher.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFailCreateIfAWishIsAlreadyAcceptedForThisCourse() throws Exception {
        Course course = helper.createCourse();
        Teacher teacher1 = helper.createEmptyTeacher();
        Teacher teacher2 = helper.createEmptyTeacher();

        perform(post(WISH_BASE_URL + "/course/" + course.getId() + "/teacher/" + teacher1.getId()))
                .andExpect(status().isCreated());
        perform(post(WISH_BASE_URL + "/accept/course/" + course.getId() + "/teacher/" + teacher1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(WishState.ACCEPTED.toString())));
        perform(post(WISH_BASE_URL + "/course/" + course.getId() + "/teacher/" + teacher2.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreate() throws Exception {
        Course course = helper.createCourse();
        Teacher teacher = helper.createEmptyTeacher();

        perform(post(WISH_BASE_URL + "/course/" + course.getId() + "/teacher/" + teacher.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.course.id", is(course.getId().intValue())))
                .andExpect(jsonPath("$.teacher.id", is(teacher.getId().intValue())))
                .andExpect(jsonPath("$.state", is(WishState.PENDING.toString())));
    }

    @Test
    public void shouldFailAcceptWishWithNonExistingCourse() throws Exception {
        perform(post(WISH_BASE_URL + "/accept/course/22/teacher/" + helper.createEmptyTeacher().getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailAcceptWishWithNonExistingTeacher() throws Exception {
        perform(post(WISH_BASE_URL + "/accept/course/" + helper.createCourse().getId() + "/teacher/22"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailAcceptWishIfWishDoesNotExists() throws Exception {
        perform(post(WISH_BASE_URL + "/accept/course/" + helper.createCourse().getId() + "/teacher/" + helper.createEmptyTeacher().getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFailAcceptWishIfAnotherIsAlreadyAccepterForThisCourse() throws Exception {
        Course course = helper.createCourse();
        Teacher teacher1 = helper.createEmptyTeacher();
        Teacher teacher2 = helper.createEmptyTeacher();

        helper.createWish(new Wish(course, teacher1, WishState.PENDING));
        helper.createWish(new Wish(course, teacher2, WishState.PENDING));

        perform(post(WISH_BASE_URL + "/accept/course/" + course.getId() + "/teacher/" + teacher1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(WishState.ACCEPTED.toString())));
        perform(post(WISH_BASE_URL + "/accept/course/" + course.getId() + "/teacher/" + teacher1.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldAcceptWish() throws Exception {
        Course course = helper.createCourse();
        Teacher teacher1 = helper.createEmptyTeacher();

        helper.createWish(new Wish(course, teacher1, WishState.PENDING));

        perform(post(WISH_BASE_URL + "/accept/course/" + course.getId() + "/teacher/" + teacher1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is(WishState.ACCEPTED.toString())));
    }

}
