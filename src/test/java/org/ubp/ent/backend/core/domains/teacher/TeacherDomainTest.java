package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class TeacherDomainTest {

    public static TeacherDomain createOne() {
        return new TeacherDomain(TeacherTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new TeacherDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Teacher model = TeacherTest.createOne();
        model.setId(12L);
        TeacherDomain domain = new TeacherDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName().toModel()).isEqualTo(model.getName());
        assertThat(domain.getContact().toModel()).isEqualTo(model.getContact());
    }

    @Test
    public void shouldTransformToModel() {
        TeacherDomain domain = TeacherDomainTest.createOne();
        domain.setId(12L);
        Teacher model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName().toModel());
        assertThat(model.getContact()).isEqualTo(domain.getContact().toModel());
    }

}