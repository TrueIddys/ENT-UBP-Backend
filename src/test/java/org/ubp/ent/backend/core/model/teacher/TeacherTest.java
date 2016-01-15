package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.contact.ContactTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class TeacherTest {

    public static Teacher createOne() {
        return new Teacher(NameTest.createOne(), ContactTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Teacher(null, ContactTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullContact() {
        new Teacher(NameTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Contact contact = ContactTest.createOne();
        Teacher teacher = new Teacher(name, contact);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getContact()).isEqualTo(contact);
    }


}