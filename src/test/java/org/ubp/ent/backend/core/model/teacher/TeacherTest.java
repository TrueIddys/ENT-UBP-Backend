package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.contact.ContactTest;
import org.ubp.ent.backend.core.model.teacher.name.Name;
import org.ubp.ent.backend.core.model.teacher.name.NameTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class TeacherTest {

    private static DefaultTeacher createOne(Name name, Contact contact) {
        return new DefaultTeacher(name, contact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new DefaultTeacher(null, ContactTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullContact() {
        new DefaultTeacher(NameTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Contact contact = ContactTest.createOne();
        DefaultTeacher teacher = new DefaultTeacher(name, contact);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getContact()).isEqualTo(contact);
    }

    private static class DefaultTeacher extends Teacher {

        public DefaultTeacher(Name name, Contact contact) {
            super(name, contact);
        }

    }

}
