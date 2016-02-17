package org.ubp.ent.backend.core.domains.wish;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomainTest;
import org.ubp.ent.backend.core.domains.course.CourseDomain;
import org.ubp.ent.backend.core.domains.course.CourseDomainTest;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;
import org.ubp.ent.backend.core.domains.teacher.UniversityTeacherDomain;
import org.ubp.ent.backend.core.domains.teacher.UniversityTeacherDomainTest;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.wish.Wish;
import org.ubp.ent.backend.core.model.wish.WishTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 17/02/2016.
 */
public class WishDomainTest {

    public static WishDomain createOne(CourseDomain course, TeacherDomain teacher) {
        Wish model = WishTest.createOne(course.toModel(), teacher.toModel());
        return new WishDomain(model);
    }

    public static WishDomain createOne() {
        return createOne(CourseDomainTest.createOne(), UniversityTeacherDomainTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullWish() {
        new WishDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Wish model = WishTest.createOne();
        model.getCourse().setId(1L);
        model.getTeacher().setId(12L);
        WishDomain domain = new WishDomain(model);

        assertThat(domain.getCourse().toModel()).isEqualTo(model.getCourse());
        assertThat(domain.getTeacher().toModel()).isEqualTo(model.getTeacher());
    }

    @Test
    public void shouldTransformToModel() {
        Wish model = WishTest.createOne();
        model.getCourse().setId(1L);
        model.getTeacher().setId(12L);
        WishDomain domain = new WishDomain(model);

        Wish domainToModel = domain.toModel();
        assertThat(domainToModel.getCourse()).isEqualTo(model.getCourse());
        assertThat(domainToModel.getTeacher()).isEqualTo(model.getTeacher());
    }

}
