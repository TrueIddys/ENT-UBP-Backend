package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class OutsiderTeacherDomainTest {

    public static OutsiderTeacherDomain createOne() {
        return new OutsiderTeacherDomain(OutsiderTeacherTest.createOne());
    }

    @Test
    public void shouldCreateFromModel() {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        model.setId(12L);
        OutsiderTeacherDomain domain = new OutsiderTeacherDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName().toModel()).isEqualTo(model.getName());
        assertThat(domain.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(domain.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(domain.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
    }

    @Test
    public void shouldTransformToModel() {
        OutsiderTeacherDomain domain = OutsiderTeacherDomainTest.createOne();
        domain.setId(12L);
        OutsiderTeacher model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName().toModel());
        assertThat(model.getContact().getAddresses()).hasSameSizeAs(domain.getContact().getAddresses());
        assertThat(model.getContact().getEmails()).hasSameSizeAs(domain.getContact().getEmails());
        assertThat(model.getContact().getPhones()).hasSameSizeAs(domain.getContact().getPhones());
    }

    @Test
    public void shouldBeEqualById() {
        OutsiderTeacherDomain first = OutsiderTeacherDomainTest.createOne();
        first.setId(1L);
        OutsiderTeacherDomain second = OutsiderTeacherDomainTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        OutsiderTeacherDomain first = OutsiderTeacherDomainTest.createOne();
        first.setId(1L);
        OutsiderTeacherDomain second = OutsiderTeacherDomainTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        OutsiderTeacherDomain first = OutsiderTeacherDomainTest.createOne();
        OutsiderTeacherDomain second = OutsiderTeacherDomainTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }

}
