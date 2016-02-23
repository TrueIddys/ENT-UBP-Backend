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

    public static Teacher createOne() {
        return createOne(NameTest.createOne(), ContactTest.createOne());
    }

    private static Teacher createOne(Name name, Contact contact) {
        return createOne(name, contact, TeacherType.UNIVERSITY_TEACHER);
    }

    private static Teacher createOne(Name name, Contact contact, TeacherType type) {
        return new Teacher(name, contact, type);
    }

    public static Teacher createOneEmpty() {
        return createOne(NameTest.createOne(), ContactTest.createOneEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Teacher(null, ContactTest.createOne(), TeacherType.UNIVERSITY_TEACHER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullContact() {
        new Teacher(NameTest.createOne(), null, TeacherType.UNIVERSITY_TEACHER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new Teacher(NameTest.createOne(), ContactTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Contact contact = ContactTest.createOne();
        Teacher teacher = new Teacher(name, contact, TeacherType.UNIVERSITY_TEACHER);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getContact()).isEqualTo(contact);
        assertThat(teacher.getType()).isEqualTo(TeacherType.UNIVERSITY_TEACHER);
    }

    @Test
    public void shouldBeEqualById() {
        Teacher first = TeacherTest.createOne();
        first.setId(1L);
        Teacher second = TeacherTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        Teacher first = TeacherTest.createOne();
        first.setId(1L);
        Teacher second = TeacherTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        Teacher first = TeacherTest.createOne();
        Teacher second = TeacherTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }

}
