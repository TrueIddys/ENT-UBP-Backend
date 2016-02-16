package org.ubp.ent.backend.core.dao.manager.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.teacher.OutsiderTeacherRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.email.EmailRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.phone.PhoneRepository;
import org.ubp.ent.backend.core.domains.teacher.OutsiderTeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.*;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTest;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTest;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class OutsiderTeacherManagerTest extends WebApplicationTest {

    @Inject
    private OutsiderTeacherManager outsiderTeacherManager;
    @Inject
    private OutsiderTeacherRepository outsiderTeacherRepository;

    @Inject
    private AddressRepository addressRepository;
    @Inject
    private EmailRepository emailRepository;
    @Inject
    private PhoneRepository phoneRepository;

    @Inject
    private TestScenarioHelper scenarioHelper;


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        outsiderTeacherManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        model.setId(12L);
        outsiderTeacherManager.create(model);
    }

    @Test
    public void shouldCreate() {
        OutsiderTeacher model = OutsiderTeacherTest.createOneEmpty();
        OutsiderTeacher saved = outsiderTeacherManager.create(model);
        OutsiderTeacher fetched = outsiderTeacherManager.findOneById(model.getId());

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(fetched.getName()).isEqualTo(model.getName());
        assertThat(fetched.getContact()).isEqualTo(model.getContact());
    }

    @Test
    public void shouldCreateAddressAndEmailAndPhoneOnCascade() {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        scenarioHelper.createOutsiderTeacher(model);
        OutsiderTeacher fetched = outsiderTeacherManager.findOneById(model.getId());

        assertThat(addressRepository.findAll()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(emailRepository.findAll()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(phoneRepository.findAll()).hasSameSizeAs(model.getContact().getPhones());
        assertThat(fetched.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(fetched.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(fetched.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
    }

    @Test
    public void shouldDeleteAddressAndEmailAndPhoneOnCascade() {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        scenarioHelper.createOutsiderTeacher(model);
        outsiderTeacherRepository.delete(new OutsiderTeacherDomain(model));

        assertThat(addressRepository.findAll()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        outsiderTeacherManager.findOneById(null);
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExisting() {
        outsiderTeacherManager.findOneById(156L);
    }

    @Test
    public void shouldFindOneById() {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        scenarioHelper.createOutsiderTeacher(model);

        OutsiderTeacher fetched = outsiderTeacherManager.findOneById(model.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    public void shouldFindAll() {
        scenarioHelper.createOutsiderTeacher();
        scenarioHelper.createOutsiderTeacher();
        scenarioHelper.createOutsiderTeacher();

        assertThat(outsiderTeacherManager.findAll()).hasSize(3);
    }

    /*
     * addAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullTeacherId() {
        outsiderTeacherManager.addAddress(null, AddressTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddAddressWithNonExistingTeacher() {
        AddressType type = scenarioHelper.createAddressType();
        outsiderTeacherManager.addAddress(12L, AddressTest.createOne(type));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullAddress() {
        outsiderTeacherManager.addAddress(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddAddressWithAddressHavingIdAlreadyDefined() {
        Address model = AddressTest.createOne();
        model.setId(12L);
        outsiderTeacherManager.addAddress(12L, model);
    }

    @Test(expected = AddressTypeResourceNotFoundException.class)
    public void shouldFailAddAddressWithAddressHavingNonExistingType() {
        OutsiderTeacher model = scenarioHelper.createEmptyOutsiderTeacher();
        outsiderTeacherManager.addAddress(model.getId(), AddressTest.createOne());
    }

    @Test
    public void shouldAddAddress() {
        OutsiderTeacher teacher = scenarioHelper.createEmptyOutsiderTeacher();
        Address address = AddressTest.createOne(scenarioHelper.createAddressType());

        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        Address saved = outsiderTeacherManager.addAddress(teacher.getId(), address);

        assertThat(addressRepository.findAll()).hasSize(1);
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveAddressWithNullAddressId() {
        outsiderTeacherManager.removeAddress(null);
    }

    @Test(expected = AddressResourceNotFoundException.class)
    public void shouldFailRemoveAddressWithNonExistingTeacher() {
        outsiderTeacherManager.removeAddress(12L);
    }

    @Test
    public void shouldRemoveAddress() {
        OutsiderTeacher teacher = scenarioHelper.createOutsiderTeacher();

        assertThat(teacher.getContact().getAddresses()).isNotEmpty();

        for (Address address : teacher.getContact().getAddresses()) {
            outsiderTeacherManager.removeAddress(address.getId());
        }
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        assertThat(addressRepository.findAll()).isEmpty();
    }


    /*
     * addEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullTeacherId() {
        outsiderTeacherManager.addEmail(null, EmailTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddEmailWithNonExistingTeacher() {
        EmailType type = scenarioHelper.createEmailType();
        outsiderTeacherManager.addEmail(12L, EmailTest.createOne(type));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullEmail() {
        outsiderTeacherManager.addEmail(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddEmailWithEmailHavingIdAlreadyDefined() {
        Email model = EmailTest.createOne();
        model.setId(12L);
        outsiderTeacherManager.addEmail(12L, model);
    }

    @Test(expected = EmailTypeResourceNotFoundException.class)
    public void shouldFailAddEmailWithEmailHavingNonExistingType() {
        OutsiderTeacher model = scenarioHelper.createEmptyOutsiderTeacher();
        outsiderTeacherManager.addEmail(model.getId(), EmailTest.createOne());
    }

    @Test
    public void shouldAddEmail() {
        OutsiderTeacher teacher = scenarioHelper.createEmptyOutsiderTeacher();
        Email email = EmailTest.createOne(scenarioHelper.createEmailType());

        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        Email saved = outsiderTeacherManager.addEmail(teacher.getId(), email);

        assertThat(emailRepository.findAll()).hasSize(1);
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveEmailWithNullEmailId() {
        outsiderTeacherManager.removeEmail(null);
    }

    @Test(expected = EmailResourceNotFoundException.class)
    public void shouldFailRemoveEmailWithNonExistingTeacher() {
        outsiderTeacherManager.removeEmail(12L);
    }

    @Test
    public void shouldRemoveEmail() {
        OutsiderTeacher teacher = scenarioHelper.createOutsiderTeacher();

        assertThat(teacher.getContact().getEmails()).isNotEmpty();

        for (Email email : teacher.getContact().getEmails()) {
            outsiderTeacherManager.removeEmail(email.getId());
        }
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
    }


    /*
     * addPhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullTeacherId() {
        outsiderTeacherManager.addPhone(null, PhoneTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddPhoneWithNonExistingTeacher() {
        outsiderTeacherManager.addPhone(12L, PhoneTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullPhone() {
        outsiderTeacherManager.addPhone(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddPhoneWithPhoneHavingIdAlreadyDefined() {
        Phone model = PhoneTest.createOne();
        model.setId(12L);
        outsiderTeacherManager.addPhone(12L, model);
    }

    @Test
    public void shouldAddPhone() {
        OutsiderTeacher teacher = scenarioHelper.createEmptyOutsiderTeacher();
        Phone phone = PhoneTest.createOne();

        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        Phone saved = outsiderTeacherManager.addPhone(teacher.getId(), phone);

        assertThat(phoneRepository.findAll()).hasSize(1);
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removePhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemovePhoneWithNullPhoneId() {
        outsiderTeacherManager.removePhone(null);
    }

    @Test(expected = PhoneResourceNotFoundException.class)
    public void shouldFailRemovePhoneWithNonExistingTeacher() {
        outsiderTeacherManager.removePhone(12L);
    }

    @Test
    public void shouldRemovePhone() {
        OutsiderTeacher teacher = scenarioHelper.createOutsiderTeacher();

        assertThat(teacher.getContact().getPhones()).isNotEmpty();

        for (Phone phone : teacher.getContact().getPhones()) {
            outsiderTeacherManager.removePhone(phone.getId());
        }
        assertThat(outsiderTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

}
