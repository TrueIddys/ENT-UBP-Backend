package org.ubp.ent.backend.core.domains.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.contact.ContactTest;
import org.ubp.ent.backend.core.model.teacher.name.Name;
import org.ubp.ent.backend.core.model.teacher.name.NameTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class TeacherDomainTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new DefaultTeacherDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        DefaultTeacher model = new DefaultTeacher(NameTest.createOne(), ContactTest.createOne());
        model.setId(12L);
        DefaultTeacherDomain domain = new DefaultTeacherDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName().toModel()).isEqualTo(model.getName());
        assertThat(domain.getContact().toModel()).isEqualTo(model.getContact());
    }


    private static class DefaultTeacherDomain extends TeacherDomain<DefaultTeacher> {

        public DefaultTeacherDomain() {
        }

        public DefaultTeacherDomain(DefaultTeacher model) {
            super(model);
        }

        @Override
        public DefaultTeacher toModel() {
            return null;
        }
    }

    private static class DefaultTeacher extends Teacher {

        public DefaultTeacher(Name name, Contact contact) {
            super(name, contact);
        }
    }

}