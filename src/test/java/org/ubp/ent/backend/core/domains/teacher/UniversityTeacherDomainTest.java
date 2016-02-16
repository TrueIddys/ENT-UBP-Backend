package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class UniversityTeacherDomainTest {

    public static UniversityTeacherDomain createOne() {
        return new UniversityTeacherDomain(UniversityTeacherTest.createOne());
    }

    @Test
    public void shouldCreateFromModel() {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        model.setId(12L);
        UniversityTeacherDomain domain = new UniversityTeacherDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName().toModel()).isEqualTo(model.getName());
        assertThat(domain.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(domain.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(domain.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
    }

    @Test
    public void shouldTransformToModel() {
        UniversityTeacherDomain domain = UniversityTeacherDomainTest.createOne();
        domain.setId(12L);
        UniversityTeacher model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName().toModel());
        assertThat(model.getContact().getAddresses()).hasSameSizeAs(domain.getContact().getAddresses());
        assertThat(model.getContact().getEmails()).hasSameSizeAs(domain.getContact().getEmails());
        assertThat(model.getContact().getPhones()).hasSameSizeAs(domain.getContact().getPhones());
    }

}
