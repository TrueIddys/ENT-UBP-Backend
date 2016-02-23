package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class TeacherDomainTest {

    public static TeacherDomain createOne() {
        return createOne(TeacherTest.createOne());
    }

    public static TeacherDomain createOne(Teacher model) {
        return new TeacherDomain(model);
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
        assertThat(domain.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(domain.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(domain.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
        assertThat(domain.getType()).isEqualTo(model.getType());
    }

    @Test
    public void shouldTransformToModel() {
        TeacherDomain domain = TeacherDomainTest.createOne();
        domain.setId(12L);
        Teacher model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName().toModel());
        assertThat(model.getContact().getAddresses()).hasSameSizeAs(domain.getContact().getAddresses());
        assertThat(model.getContact().getEmails()).hasSameSizeAs(domain.getContact().getEmails());
        assertThat(model.getContact().getPhones()).hasSameSizeAs(domain.getContact().getPhones());
        assertThat(model.getType()).isEqualTo(domain.getType());
    }

    @Test
    public void shouldBeEqualById() {
        TeacherDomain first = TeacherDomainTest.createOne();
        first.setId(1L);
        TeacherDomain second = TeacherDomainTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        TeacherDomain first = TeacherDomainTest.createOne();
        first.setId(1L);
        TeacherDomain second = TeacherDomainTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        TeacherDomain first = TeacherDomainTest.createOne();
        TeacherDomain second = TeacherDomainTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }

}
