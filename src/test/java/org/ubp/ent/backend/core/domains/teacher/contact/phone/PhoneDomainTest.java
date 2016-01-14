package org.ubp.ent.backend.core.domains.teacher.contact.phone;

import org.junit.Test;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTest;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 14/01/2016.
 */
public class PhoneDomainTest {

    public static PhoneDomain createOne() {
        return new PhoneDomain(PhoneTest.createOne());
    }

    public static PhoneDomain createOne(String PhoneTypeDomain) {
        return new PhoneDomain(PhoneTest.createOne(PhoneTypeDomain));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new PhoneDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Phone model = PhoneTest.createOne();
        model.setId(12L);
        PhoneDomain domain = new PhoneDomain(model);

        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getType().toModel()).isEqualTo(model.getType());
        assertThat(domain.getDetails().toModel()).isEqualTo(model.getDetails());
    }

    @Test
    public void shouldTransformToModel() {
        PhoneDomain domain = createOne();
        domain.setId(12L);
        Phone model = domain.toModel();

        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getType()).isEqualTo(domain.getType().toModel());
        assertThat(model.getDetails()).isEqualTo(domain.getDetails().toModel());
    }

    @Test
    public void shouldBeEqualsBasedOnType() {
        PhoneDomain first = createOne("Home");
        PhoneDomain second = createOne("Home");
        assertThat(second).isEqualTo(first);
    }

    @Test
    public void shouldNotBeEqualsWithDifferentTypes() {
        PhoneDomain first = createOne("Home");
        PhoneDomain second = createOne("Work");
        assertThat(second).isNotEqualTo(first);
    }

}