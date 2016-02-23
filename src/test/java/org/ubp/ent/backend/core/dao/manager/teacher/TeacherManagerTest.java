package org.ubp.ent.backend.core.dao.manager.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.teacher.TeacherRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.email.EmailRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.phone.PhoneRepository;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.AddressResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EmailResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.PhoneResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailTest;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneTest;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 16/01/2016.
 */
public class TeacherManagerTest extends WebApplicationTest {

    @Inject
    private TeacherManager teacherManager;
    @Inject
    private TeacherRepository teacherRepository;

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
        teacherManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateIfIdIsAlreadyDefined() {
        Teacher model = TeacherTest.createOne();
        model.setId(12L);
        teacherManager.create(model);
    }

    @Test
    public void shouldCreate() {
        Teacher model = TeacherTest.createOneEmpty();
        Teacher saved = teacherManager.create(model);
        Teacher fetched = teacherManager.findOneById(model.getId());

        assertThat(model.getId()).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(fetched.getName()).isEqualTo(model.getName());
        assertThat(fetched.getContact()).isEqualTo(model.getContact());
        assertThat(fetched.getType()).isEqualTo(model.getType());
    }

    @Test
    public void shouldCreateAddressAndEmailAndPhoneOnCascade() {
        Teacher model = TeacherTest.createOne();
        scenarioHelper.createTeacher(model);
        Teacher fetched = teacherManager.findOneById(model.getId());

        assertThat(addressRepository.findAll()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(emailRepository.findAll()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(phoneRepository.findAll()).hasSameSizeAs(model.getContact().getPhones());
        assertThat(fetched.getContact().getAddresses()).hasSameSizeAs(model.getContact().getAddresses());
        assertThat(fetched.getContact().getEmails()).hasSameSizeAs(model.getContact().getEmails());
        assertThat(fetched.getContact().getPhones()).hasSameSizeAs(model.getContact().getPhones());
        assertThat(fetched.getType()).isEqualTo(model.getType());
    }

    @Test
    public void shouldDeleteAddressAndEmailAndPhoneOnCascade() {
        Teacher model = TeacherTest.createOne();
        scenarioHelper.createTeacher(model);
        teacherRepository.delete(new TeacherDomain(model));

        assertThat(addressRepository.findAll()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        teacherManager.findOneById(null);
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExisting() {
        teacherManager.findOneById(156L);
    }

    @Test
    public void shouldFindOneById() {
        Teacher model = TeacherTest.createOne();
        scenarioHelper.createTeacher(model);

        Teacher fetched = teacherManager.findOneById(model.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    public void shouldFindAll() {
        scenarioHelper.createTeacher();
        scenarioHelper.createTeacher();
        scenarioHelper.createTeacher();

        assertThat(teacherManager.findAll()).hasSize(3);
    }

    /*
     * addAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullTeacherId() {
        teacherManager.addAddress(null, AddressTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddAddressWithNonExistingTeacher() {
        teacherManager.addAddress(12L, AddressTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullAddress() {
        teacherManager.addAddress(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddAddressWithAddressHavingIdAlreadyDefined() {
        Address model = AddressTest.createOne();
        model.setId(12L);
        teacherManager.addAddress(12L, model);
    }

    @Test
    public void shouldAddAddress() {
        Teacher teacher = scenarioHelper.createEmptyTeacher();
        Address address = AddressTest.createOne();

        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        Address saved = teacherManager.addAddress(teacher.getId(), address);

        assertThat(addressRepository.findAll()).hasSize(1);
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getAddresses()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeAddress()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveAddressWithNullAddressId() {
        teacherManager.removeAddress(null);
    }

    @Test(expected = AddressResourceNotFoundException.class)
    public void shouldFailRemoveAddressWithNonExistingTeacher() {
        teacherManager.removeAddress(12L);
    }

    @Test
    public void shouldRemoveAddress() {
        Teacher teacher = scenarioHelper.createTeacher();

        assertThat(teacher.getContact().getAddresses()).isNotEmpty();

        for (Address address : teacher.getContact().getAddresses()) {
            teacherManager.removeAddress(address.getId());
        }
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getAddresses()).isEmpty();
        assertThat(addressRepository.findAll()).isEmpty();
    }


    /*
     * addEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullTeacherId() {
        teacherManager.addEmail(null, EmailTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddEmailWithNonExistingTeacher() {
        teacherManager.addEmail(12L, EmailTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddEmailWithNullEmail() {
        teacherManager.addEmail(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddEmailWithEmailHavingIdAlreadyDefined() {
        Email model = EmailTest.createOne();
        model.setId(12L);
        teacherManager.addEmail(12L, model);
    }

    @Test
    public void shouldAddEmail() {
        Teacher teacher = scenarioHelper.createEmptyTeacher();
        Email email = EmailTest.createOne();

        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        Email saved = teacherManager.addEmail(teacher.getId(), email);

        assertThat(emailRepository.findAll()).hasSize(1);
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getEmails()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removeEmail()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemoveEmailWithNullEmailId() {
        teacherManager.removeEmail(null);
    }

    @Test(expected = EmailResourceNotFoundException.class)
    public void shouldFailRemoveEmailWithNonExistingTeacher() {
        teacherManager.removeEmail(12L);
    }

    @Test
    public void shouldRemoveEmail() {
        Teacher teacher = scenarioHelper.createTeacher();

        assertThat(teacher.getContact().getEmails()).isNotEmpty();

        for (Email email : teacher.getContact().getEmails()) {
            teacherManager.removeEmail(email.getId());
        }
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getEmails()).isEmpty();
        assertThat(emailRepository.findAll()).isEmpty();
    }


    /*
     * addPhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullTeacherId() {
        teacherManager.addPhone(null, PhoneTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddPhoneWithNonExistingTeacher() {
        teacherManager.addPhone(12L, PhoneTest.createOne());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddPhoneWithNullPhone() {
        teacherManager.addPhone(12L, null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailAddPhoneWithPhoneHavingIdAlreadyDefined() {
        Phone model = PhoneTest.createOne();
        model.setId(12L);
        teacherManager.addPhone(12L, model);
    }

    @Test
    public void shouldAddPhone() {
        Teacher teacher = scenarioHelper.createEmptyTeacher();
        Phone phone = PhoneTest.createOne();

        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        Phone saved = teacherManager.addPhone(teacher.getId(), phone);

        assertThat(phoneRepository.findAll()).hasSize(1);
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getPhones()).hasSize(1);
        assertThat(saved.getId()).isNotNull();
    }

    /*
     * removePhone()
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailRemovePhoneWithNullPhoneId() {
        teacherManager.removePhone(null);
    }

    @Test(expected = PhoneResourceNotFoundException.class)
    public void shouldFailRemovePhoneWithNonExistingTeacher() {
        teacherManager.removePhone(12L);
    }

    @Test
    public void shouldRemovePhone() {
        Teacher teacher = scenarioHelper.createTeacher();

        assertThat(teacher.getContact().getPhones()).isNotEmpty();

        for (Phone phone : teacher.getContact().getPhones()) {
            teacherManager.removePhone(phone.getId());
        }
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getPhones()).isEmpty();
        assertThat(phoneRepository.findAll()).isEmpty();
    }

}
