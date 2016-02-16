package org.ubp.ent.backend.core.dao.manager.teacher;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.email.EmailManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.phone.PhoneManager;
import org.ubp.ent.backend.core.dao.repository.teacher.UniversityTeacherRepository;
import org.ubp.ent.backend.core.domains.teacher.UniversityTeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 15/01/2016.
 */
@Service
public class UniversityTeacherManager {

    @Inject
    private UniversityTeacherRepository universityTeacherRepository;

    @Inject
    private AddressManager addressManager;

    @Inject
    private EmailManager emailManager;

    @Inject
    private PhoneManager phoneManager;

    public UniversityTeacher create(UniversityTeacher model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + UniversityTeacher.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + UniversityTeacher.class.getName() + " which already has an ID.");
        }
        UniversityTeacherDomain domain = new UniversityTeacherDomain(model);
        domain = universityTeacherRepository.saveAndFlush(domain);
        model.setId(domain.getId());

        return domain.toModel();
    }

    public UniversityTeacher findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + UniversityTeacher.class.getName() + " with a null id.");
        }
        UniversityTeacherDomain domain = universityTeacherRepository.findOne(id);

        if (domain == null) {
            throw new TeacherResourceNotFoundException("No " + UniversityTeacher.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<UniversityTeacher> findAll() {
        return universityTeacherRepository.findAll().parallelStream()
                .map(UniversityTeacherDomain::toModel)
                .collect(Collectors.toList());
    }

    public Address addAddress(Long teacherId, Address model) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + UniversityTeacher.class.getName() + " with a null id.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Address.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Address.class.getName() + " which already has an ID.");
        }

        model = addressManager.create(model);

        UniversityTeacher fetched = findOneById(teacherId);
        fetched.getContact().addAddress(model);
        universityTeacherRepository.saveAndFlush(new UniversityTeacherDomain(fetched));
        return model;
    }

    public void removeAddress(Long addressId) {
        addressManager.delete(addressId);
    }

    public Email addEmail(Long teacherId, Email model) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + UniversityTeacher.class.getName() + " with a null id.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Email.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Email.class.getName() + " which already has an ID.");
        }

        model = emailManager.create(model);

        UniversityTeacher fetched = findOneById(teacherId);
        fetched.getContact().addEmail(model);
        universityTeacherRepository.saveAndFlush(new UniversityTeacherDomain(fetched));
        return model;
    }

    public void removeEmail(Long emailId) {
        emailManager.delete(emailId);
    }

    public Phone addPhone(Long teacherId, Phone model) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + UniversityTeacher.class.getName() + " with a null id.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Phone.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Phone.class.getName() + " which already has an ID.");
        }

        model = phoneManager.create(model);

        UniversityTeacher fetched = findOneById(teacherId);
        fetched.getContact().addPhone(model);
        universityTeacherRepository.saveAndFlush(new UniversityTeacherDomain(fetched));
        return model;
    }

    public void removePhone(Long phoneId) {
        phoneManager.delete(phoneId);
    }

}
