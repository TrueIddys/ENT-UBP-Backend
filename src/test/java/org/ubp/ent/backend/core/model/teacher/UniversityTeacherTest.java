package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.contact.ContactTest;
import org.ubp.ent.backend.core.model.teacher.name.Name;
import org.ubp.ent.backend.core.model.teacher.name.NameTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class UniversityTeacherTest {

    public static UniversityTeacher createOne() {
        return createOne(NameTest.createOne(), ContactTest.createOne());
    }

    public static UniversityTeacher createOne(Name name, Contact contact) {
        return new UniversityTeacher(name, contact);
    }

    public static UniversityTeacher createOneEmpty() {
        return createOne(NameTest.createOne(), ContactTest.createOneEmpty());
    }


    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Contact contact = ContactTest.createOne();
        UniversityTeacher teacher = new UniversityTeacher(name, contact);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getContact()).isEqualTo(contact);
    }

    @Test
    public void shouldBeEqualById() {
        Teacher first = UniversityTeacherTest.createOne();
        first.setId(1L);
        Teacher second = UniversityTeacherTest.createOne();
        second.setId(1L);

        assertThat(first).isEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        Teacher first = UniversityTeacherTest.createOne();
        first.setId(1L);
        Teacher second = UniversityTeacherTest.createOne();
        second.setId(2L);

        assertThat(first).isNotEqualTo(second);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        Teacher first = UniversityTeacherTest.createOne();
        Teacher second = UniversityTeacherTest.createOne();

        assertThat(first).isNotEqualTo(second);
    }

}
