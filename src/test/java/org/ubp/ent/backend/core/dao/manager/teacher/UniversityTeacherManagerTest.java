package org.ubp.ent.backend.core.dao.manager.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.teacher.UniversityTeacherRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.email.EmailRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.phone.PhoneRepository;
import org.ubp.ent.backend.core.domains.teacher.UniversityTeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.*;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTest;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTest;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 15/01/2016.
 */
public class UniversityTeacherManagerTest extends WebApplicationTest {

    @Inject
    private UniversityTeacherManager universityTeacherManager;
    @Inject
    private UniversityTeacherRepository universityTeacherRepository;

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
        universityTeacherManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        model.setId(12L);
        universityTeacherManager.create(model);
    }

    @Test
    public void shouldCreate() {
        UniversityTeacher model = UniversityTeacherTest.createOneEmpty();
        UniversityTeacher saved = universityTeacherManager.create(model);
        UniversityTeacher fetched = universityTeacherManager.findOneById(model.getId());

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(fetched.getName()).isEqualTo(model.getName());
        assertThat(fetched.getContact()).isEqualTo(model.getContact());
    }

    @Test
    public void shouldCreateAddressAndEmailAndPhoneOnCascade() {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        scenarioHelper.createUniversityTeacher(model);
        UniversityTeacher fetched = universityTeacherManager.findOneById(model.getId());

        assertThat(addressRepository.findAll()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(emailRepository.findAll()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(phoneRepository.findAll()).hasSameSizeAs(model.getContact().getPhones());
        assertThat(fetched.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(fetched.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(fetched.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
    }

    @Test
    public void shouldDeleteAddressAndEmailAndPhoneOnCascade() {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        scenarioHelper.createUniversityTeacher(model);
        universityTeacherRepository.delete(new UniversityTeacherDomain(model));

        assertThat(addressRepository.findAll()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        universityTeacherManager.findOneById(null);
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExisting() {
        universityTeacherManager.findOneById(156L);
    }

    @Test
    public void shouldFindOneById() {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        scenarioHelper.createUniversityTeacher(model);

        UniversityTeacher fetched = universityTeacherManager.findOneById(model.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    public void shouldFindAll() {
        scenarioHelper.createUniversityTeacher();
        scenarioHelper.createUniversityTeacher();
        scenarioHelper.createUniversityTeacher();

        assertThat(universityTeacherManager.findAll()).hasSize(3);
    }

    /*
     * addAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullTeacherId() {
        universityTeacherManager.addAddress(null, AddressTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddAddressWithNonExistingTeacher() {
        AddressType type = scenarioHelper.createAddressType();
        universityTeacherManager.addAddress(12L, AddressTest.createOne(type));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullAddress() {
        universityTeacherManager.addAddress(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddAddressWithAddressHavingIdAlreadyDefined() {
        Address model = AddressTest.createOne();
        model.setId(12L);
        universityTeacherManager.addAddress(12L, model);
    }

    @Test(expected = AddressTypeResourceNotFoundException.class)
    public void shouldFailAddAddressWithAddressHavingNonExistingType() {
        UniversityTeacher model = scenarioHelper.createEmptyUniversityTeacher();
        universityTeacherManager.addAddress(model.getId(), AddressTest.createOne());
    }

    @Test
    public void shouldAddAddress() {
        UniversityTeacher teacher = scenarioHelper.createEmptyUniversityTeacher();
        Address address = AddressTest.createOne(scenarioHelper.createAddressType());

        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        Address saved = universityTeacherManager.addAddress(teacher.getId(), address);

        assertThat(addressRepository.findAll()).hasSize(1);
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveAddressWithNullAddressId() {
        universityTeacherManager.removeAddress(null);
    }

    @Test(expected = AddressResourceNotFoundException.class)
    public void shouldFailRemoveAddressWithNonExistingTeacher() {
        universityTeacherManager.removeAddress(12L);
    }

    @Test
    public void shouldRemoveAddress() {
        UniversityTeacher teacher = scenarioHelper.createUniversityTeacher();

        assertThat(teacher.getContact().getAddresses()).isNotEmpty();

        for (Address address : teacher.getContact().getAddresses()) {
            universityTeacherManager.removeAddress(address.getId());
        }
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        assertThat(addressRepository.findAll()).isEmpty();
    }


    /*
     * addEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullTeacherId() {
        universityTeacherManager.addEmail(null, EmailTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddEmailWithNonExistingTeacher() {
        universityTeacherManager.addEmail(12L, EmailTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullEmail() {
        universityTeacherManager.addEmail(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddEmailWithEmailHavingIdAlreadyDefined() {
        Email model = EmailTest.createOne();
        model.setId(12L);
        universityTeacherManager.addEmail(12L, model);
    }

    @Test
    public void shouldAddEmail() {
        UniversityTeacher teacher = scenarioHelper.createEmptyUniversityTeacher();
        Email email = EmailTest.createOne();

        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        Email saved = universityTeacherManager.addEmail(teacher.getId(), email);

        assertThat(emailRepository.findAll()).hasSize(1);
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveEmailWithNullEmailId() {
        universityTeacherManager.removeEmail(null);
    }

    @Test(expected = EmailResourceNotFoundException.class)
    public void shouldFailRemoveEmailWithNonExistingTeacher() {
        universityTeacherManager.removeEmail(12L);
    }

    @Test
    public void shouldRemoveEmail() {
        UniversityTeacher teacher = scenarioHelper.createUniversityTeacher();

        assertThat(teacher.getContact().getEmails()).isNotEmpty();

        for (Email email : teacher.getContact().getEmails()) {
            universityTeacherManager.removeEmail(email.getId());
        }
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
    }


    /*
     * addPhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullTeacherId() {
        universityTeacherManager.addPhone(null, PhoneTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddPhoneWithNonExistingTeacher() {
        universityTeacherManager.addPhone(12L, PhoneTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullPhone() {
        universityTeacherManager.addPhone(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddPhoneWithPhoneHavingIdAlreadyDefined() {
        Phone model = PhoneTest.createOne();
        model.setId(12L);
        universityTeacherManager.addPhone(12L, model);
    }

    @Test
    public void shouldAddPhone() {
        UniversityTeacher teacher = scenarioHelper.createEmptyUniversityTeacher();
        Phone phone = PhoneTest.createOne();

        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        Phone saved = universityTeacherManager.addPhone(teacher.getId(), phone);

        assertThat(phoneRepository.findAll()).hasSize(1);
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removePhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemovePhoneWithNullPhoneId() {
        universityTeacherManager.removePhone(null);
    }

    @Test(expected = PhoneResourceNotFoundException.class)
    public void shouldFailRemovePhoneWithNonExistingTeacher() {
        universityTeacherManager.removePhone(12L);
    }

    @Test
    public void shouldRemovePhone() {
        UniversityTeacher teacher = scenarioHelper.createUniversityTeacher();

        assertThat(teacher.getContact().getPhones()).isNotEmpty();

        for (Phone phone : teacher.getContact().getPhones()) {
            universityTeacherManager.removePhone(phone.getId());
        }
        assertThat(universityTeacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

}
