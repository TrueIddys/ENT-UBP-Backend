package org.ubp.ent.backend.utils;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;
import org.ubp.ent.backend.core.dao.manager.teacher.OutsiderTeacherManager;
import org.ubp.ent.backend.core.dao.manager.teacher.UniversityTeacherManager;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;

import javax.inject.Inject;

/**
 * Created by Anthony on 15/01/2016.
 */
@Component
@Conditional(value = {TestProfileCondition.class})
public class TestScenarioHelper {

    @Inject
    private UniversityTeacherManager universityTeacherM;
    @Inject
    private OutsiderTeacherManager outsiderTeacherM;

    /*
     * UniversityTeacher
     */
    public UniversityTeacher createUniversityTeacher() {
        return createUniversityTeacher(UniversityTeacherTest.createOne());
    }

    public UniversityTeacher createUniversityTeacher(UniversityTeacher model) {
        return universityTeacherM.create(model);
    }

    public UniversityTeacher createEmptyUniversityTeacher() {
        return createUniversityTeacher(UniversityTeacherTest.createOneEmpty());
    }

    /*
     * OutsiderTeacher
     */
    public OutsiderTeacher createOutsiderTeacher() {
        return createOutsiderTeacher(OutsiderTeacherTest.createOne());
    }

    public OutsiderTeacher createOutsiderTeacher(OutsiderTeacher model) {
        return outsiderTeacherM.create(model);
    }

    public OutsiderTeacher createEmptyOutsiderTeacher() {
        return createOutsiderTeacher(OutsiderTeacherTest.createOneEmpty());
    }

}
