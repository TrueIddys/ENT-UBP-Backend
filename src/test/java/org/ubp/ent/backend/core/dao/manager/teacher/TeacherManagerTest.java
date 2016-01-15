package org.ubp.ent.backend.core.dao.manager.teacher;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.teacher.TeacherRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.email.EmailRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.phone.PhoneRepository;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.AddressTypeResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 15/01/2016.
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
    }

    @Test
    public void shouldDeleteAddressAndEmailAndPhoneOnCascade() {
        Teacher model = TeacherTest.createOne();
        scenarioHelper.createTeacher(model);
        teacherRepository.delete(new TeacherDomain(model));

        assertThat(addressRepository.findAll()).hasSize(0);
        assertThat(emailRepository.findAll()).hasSize(0);
        assertThat(phoneRepository.findAll()).hasSize(0);
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddAddressWithNullTeacherId() {
        teacherManager.addAddress(null, AddressTest.createOne());
    }

    @Test(expected = TeacherResourceNotFoundException.class)
    public void shouldFailAddAddressWithNonExistingTeacher() {
        AddressType type = scenarioHelper.createAddressType();
        teacherManager.addAddress(12L, AddressTest.createOne(type));
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

    @Test(expected = AddressTypeResourceNotFoundException.class)
    public void shouldFailAddAddressWithAddressHavingNonExistingType() {
        Teacher model = scenarioHelper.createEmptyTeacher();
        teacherManager.addAddress(model.getId(), AddressTest.createOne());
    }

    @Test
    public void shouldAddAddress() {
        Teacher teacher = scenarioHelper.createEmptyTeacher();
        Address address = AddressTest.createOne(scenarioHelper.createAddressType());

        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getAddresses()).hasSize(0);
        teacherManager.addAddress(teacher.getId(), address);

        assertThat(addressRepository.findAll()).hasSize(1);
        assertThat(teacherManager.findOneById(teacher.getId()).getContact().getAddresses()).hasSize(1);
    }


}