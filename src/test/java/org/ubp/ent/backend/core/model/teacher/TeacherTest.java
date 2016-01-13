package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.Address;
import org.ubp.ent.backend.core.model.teacher.contact.AddressTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 11/01/2016.
 */
public class TeacherTest {

    public static Teacher createOne() {
        return new Teacher(NameTest.createOne(), AddressTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBuildWithNullName() {
        new Teacher(null, AddressTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBuildWithNullAddress() {
        new Teacher(NameTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Address address = AddressTest.createOne();
        Teacher teacher = new Teacher(name, address);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getAddress()).isEqualTo(address);
    }

}