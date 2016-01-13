package org.ubp.ent.backend.core.model.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetails;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressDetailsTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThatThrownBy;

/**
 * Created by Anthony on 11/01/2016.
 */
public class TeacherTest {

    public static Teacher createOneWithNoAddressNoPhoneNoMail() {
        return new Teacher(NameTest.createOne());
    }

    public static Teacher createOne() {
        Teacher teacher = createOneWithNoAddressNoPhoneNoMail();
        teacher.addAddress(AddressTest.createOne());

        return teacher;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new Teacher(null);
    }

    @Test
    public void shouldInstantiate() {
        Name name = NameTest.createOne();
        Teacher teacher = new Teacher(name);

        assertThat(teacher.getId()).isNull();
        assertThat(teacher.getName()).isEqualTo(name);
        assertThat(teacher.getAddresses()).isEmpty();
    }

    @Test
    public void shouldNotAddTwoAddressWithSameType() {
        Teacher teacher = createOneWithNoAddressNoPhoneNoMail();

        Address address1 = AddressTest.createOne("Home");
        teacher.addAddress(address1);

        Address address2 = AddressTest.createOne("Home");
        teacher.addAddress(address2);

        assertThat(teacher.getAddresses()).hasSize(1);
    }

}