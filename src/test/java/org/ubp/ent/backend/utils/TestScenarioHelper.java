package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.dao.manager.teacher.TeacherManager;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;

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

    private Course createCourse(Course model) {
        return courseM.create(model);
    }

}
