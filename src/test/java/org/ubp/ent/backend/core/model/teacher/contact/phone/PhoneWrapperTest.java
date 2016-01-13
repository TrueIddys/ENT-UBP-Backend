package org.ubp.ent.backend.core.model.teacher.contact.phone;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 13/01/2016.
 */
public class PhoneWrapperTest {

    public static PhoneWrapper createOne() {
        return new PhoneWrapper(PhoneTypeTest.createOne(), PhoneTest.createOne());
    }

    public static PhoneWrapper createOne(String PhoneType) {
        return new PhoneWrapper(PhoneTypeTest.createOne(PhoneType), PhoneTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullType() {
        new PhoneWrapper(null, PhoneTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullDetail() {
        new PhoneWrapper(PhoneTypeTest.createOne(), null);
    }

    @Test
    public void shouldInstantiate() {
        PhoneType type = PhoneTypeTest.createOne();
        Phone data = PhoneTest.createOne();
        PhoneWrapper model = new PhoneWrapper(type, data);

        assertThat(model.getId()).isNull();
        assertThat(model.getPhoneType()).isEqualTo(type);
        assertThat(model.getPhone()).isEqualTo(data);
    }

    @Test
    public void shouldBeEqualWithTypeAndData() {
        PhoneType type = PhoneTypeTest.createOne();
        Phone data = PhoneTest.createOne();
        PhoneWrapper model1 = new PhoneWrapper(type, data);
        PhoneWrapper model2 = new PhoneWrapper(type, data);
        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentType() {
        Phone data = PhoneTest.createOne();
        PhoneWrapper model1 = new PhoneWrapper(PhoneTypeTest.createOne("Personal"), data);
        PhoneWrapper model2 = new PhoneWrapper(PhoneTypeTest.createOne("Work"), data);
        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentData() {
        PhoneType type = PhoneTypeTest.createOne();
        PhoneWrapper model1 = new PhoneWrapper(type, PhoneTest.createOne());
        PhoneWrapper model2 = new PhoneWrapper(type, PhoneTest.createOne());
        assertThat(model1).isNotEqualTo(model2);
    }
    
}