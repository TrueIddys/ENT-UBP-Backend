package org.ubp.ent.backend.core.domains.teacher.contact;

import org.junit.Test;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDomain;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDomainTest;
import org.ubp.ent.backend.core.domains.teacher.contact.email.EmailDomain;
import org.ubp.ent.backend.core.domains.teacher.contact.email.EmailDomainTest;
import org.ubp.ent.backend.core.domains.teacher.contact.phone.PhoneDomain;
import org.ubp.ent.backend.core.domains.teacher.contact.phone.PhoneDomainTest;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.contact.ContactTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 14/01/2016.
 */
public class ContactDomainTest {

    public static ContactDomain createOneEmpty() {
        return new ContactDomain(ContactTest.createOneEmpty());
    }

    public static ContactDomain createOne() {
        return new ContactDomain(ContactTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new ContactDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        Contact model = ContactTest.createOne();
        ContactDomain domain = new ContactDomain(model);

        assertThat(domain.getAddresses()).hasSameSizeAs(model.getAddresses());
        assertThat(domain.getPhones()).hasSameSizeAs(model.getPhones());
        assertThat(domain.getEmails()).hasSameSizeAs(model.getEmails());
    }

    @Test
    public void shouldTransformToModel() {
        ContactDomain domain = createOne();
        Contact model = domain.toModel();

        assertThat(model.getAddresses()).hasSameSizeAs(domain.getAddresses());
        assertThat(model.getPhones()).hasSameSizeAs(domain.getPhones());
        assertThat(model.getEmails()).hasSameSizeAs(domain.getEmails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddNullAddress() {
        createOneEmpty().addAddress(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToModifyAddressSet() {
        ContactDomain contact = createOneEmpty();

        contact.getAddresses().add(AddressDomainTest.createOne());
    }

    @Test
    public void shouldNotAddTwoAddressWithSameType() {
        ContactDomain contact = createOneEmpty();

        AddressDomain address1 = AddressDomainTest.createOne("Home");
        contact.addAddress(address1);

        AddressDomain address2 = AddressDomainTest.createOne("Home");
        contact.addAddress(address2);

        assertThat(contact.getAddresses()).containsOnly(address2);
    }

    @Test
    public void shouldAddTwoAddressWithDifferentTypes() {
        ContactDomain contact = createOneEmpty();

        AddressDomain address1 = AddressDomainTest.createOne("Home");
        contact.addAddress(address1);

        AddressDomain address2 = AddressDomainTest.createOne("Secondary Home");
        contact.addAddress(address2);

        assertThat(contact.getAddresses()).containsOnly(address1, address2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddNullPhone() {
        createOneEmpty().addPhone(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToModifyPhoneSet() {
        ContactDomain contact = createOneEmpty();

        contact.getPhones().add(PhoneDomainTest.createOne());
    }

    @Test
    public void shouldAddTwoPhoneWithDifferentTypes() {
        ContactDomain contact = createOneEmpty();

        PhoneDomain phone1 = PhoneDomainTest.createOne();
        contact.addPhone(phone1);

        PhoneDomain phone2 = PhoneDomainTest.createOne();
        contact.addPhone(phone2);

        assertThat(contact.getPhones()).containsOnly(phone1, phone2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddNullMail() {
        createOneEmpty().addEmail(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToModifyEmailSet() {
        ContactDomain contact = createOneEmpty();

        contact.getEmails().add(EmailDomainTest.createOne());
    }

    @Test
    public void shouldNotAddTwoEmailWithSameType() {
        ContactDomain contact = createOneEmpty();

        EmailDomain email1 = EmailDomainTest.createOne("University");
        contact.addEmail(email1);

        EmailDomain email2 = EmailDomainTest.createOne("University");
        contact.addEmail(email2);

        assertThat(contact.getEmails()).containsOnly(email2);
    }

    @Test
    public void shouldAddTwoEmailWithDifferentTypes() {
        ContactDomain contact = createOneEmpty();

        EmailDomain email1 = EmailDomainTest.createOne("University");
        contact.addEmail(email1);

        EmailDomain email2 = EmailDomainTest.createOne("Personal");
        contact.addEmail(email2);

        assertThat(contact.getEmails()).containsOnly(email1, email2);
    }

}
