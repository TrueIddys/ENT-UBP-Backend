package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.dao.manager.teacher.TeacherManager;
import org.ubp.ent.backend.core.dao.manager.wish.WishManager;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;
import org.ubp.ent.backend.core.model.wish.Wish;
import org.ubp.ent.backend.core.model.wish.WishState;

import javax.inject.Inject;

/**
 * Created by Anthony on 15/01/2016.
 */
@Component
@Conditional(value = {TestProfileCondition.class})
public class TestScenarioHelper {

    @Inject
    private TeacherManager teacherM;

    @Inject
    private CourseManager courseM;

    @Inject
    private WishManager wishM;

    /*
     * Teacher
     */
    public Teacher createTeacher() {
        return createTeacher(TeacherTest.createOne());
    }

    public Teacher createTeacher(Teacher model) {
        return teacherM.create(model);
    }

    public Teacher createEmptyTeacher() {
        return createTeacher(TeacherTest.createOneEmpty());
    }


    /*
     * Course
     */
    public Course createCourse() {
        return createCourse(CourseTest.createOne());
    }

    public Course createCourse(Course model) {
        return courseM.create(model);
    }

    /*
     * Wish
     */
    public Wish createWish() {
        Course course = createCourse();
        Teacher teacher = createEmptyTeacher();
        return createWish(new Wish(course, teacher, WishState.PENDING));
    }

    public Wish createWishForCourse(Course course) {
        return createWish(new Wish(course, createEmptyTeacher(), WishState.PENDING));
    }

    public Wish createWishForTeacher(Teacher teacher) {
        return createWish(new Wish(createCourse(), teacher, WishState.PENDING));
    }

    public Wish createWish(Wish model) {
        return wishM.create(model.getCourse().getId(), model.getTeacher().getId());
    }

}
