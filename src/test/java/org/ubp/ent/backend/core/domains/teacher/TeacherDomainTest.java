package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDetailsDomain;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class TeacherDomainTest {

    public static TeacherDomain createOne() {
        return new TeacherDomain(TeacherTest.createOne());
    }

    @Test
    public void shouldCreateDomainFromModel() {
        Teacher model = TeacherTest.createOne();
        model.setId(12L);

        TeacherDomain domain = new TeacherDomain(model);
        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(new NameDetailsDomain(model.getName()));
        assertThat(domain.getAddress()).isEqualTo(new AddressDetailsDomain(model.getAddress()));
    }

    @Test
    public void shouldTransformDomainToModel() {
        Teacher model = TeacherTest.createOne();
        model.setId(12L);
        TeacherDomain domain = new TeacherDomain(model);

        Teacher newModel = domain.toModel();
        assertThat(newModel.getId()).isEqualTo(model.getId());
        assertThat(newModel.getName()).isEqualTo(model.getName());
        assertThat(newModel.getAddress()).isEqualTo(model.getAddress());
    }

    @Test
    public void shouldBeEqualsByIdIfNotNull() {
        TeacherDomain domain1 = createOne();
        domain1.setId(12L);
        TeacherDomain domain2 = createOne();
        domain2.setId(12L);

        assertThat(domain1).isEqualTo(domain2);
    }

    @Test
    public void shouldNotBeEqualsIfIdIsDifferent() {
        TeacherDomain domain1 = createOne();
        domain1.setId(12L);
        TeacherDomain domain2 = createOne();
        domain2.setId(51L);

        assertThat(domain1).isNotEqualTo(domain2);
    }

    @Test
    public void shouldNotBeEqualsByIfIdIsNull() {
        TeacherDomain domain1 = createOne();
        TeacherDomain domain2 = createOne();

        domain1.setId(null);
        domain2.setId(null);
        assertThat(domain1).isNotEqualTo(domain2);

        domain1.setId(26L);
        domain2.setId(null);
        assertThat(domain1).isNotEqualTo(domain2);


        domain1.setId(null);
        domain2.setId(12L);
        assertThat(domain1).isNotEqualTo(domain2);
    }

}