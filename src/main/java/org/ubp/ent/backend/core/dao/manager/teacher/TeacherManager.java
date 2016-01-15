package org.ubp.ent.backend.core.dao.manager.teacher;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressManager;
import org.ubp.ent.backend.core.dao.repository.teacher.TeacherRepository;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressTypeRepository;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.AddressTypeResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeacherResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 15/01/2016.
 */
@Service
public class TeacherManager {

    @Inject
    private TeacherRepository teacherRepository;

    @Inject
    private AddressManager addressManager;
    @Inject
    private AddressTypeRepository addressTypeRepository;

    public Teacher create(Teacher model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Teacher.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Teacher.class.getName() + " which already has an ID.");
        }
        TeacherDomain domain = new TeacherDomain(model);
        domain = teacherRepository.saveAndFlush(domain);
        model.setId(domain.getId());

        return domain.toModel();
    }

    public Teacher findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Teacher.class.getName() + " with a null id.");
        }
        TeacherDomain domain = teacherRepository.findOne(id);

        if (domain == null) {
            throw new TeacherResourceNotFoundException("No " + Teacher.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll().parallelStream()
                .map(TeacherDomain::toModel)
                .collect(Collectors.toList());
    }

    public void addAddress(Long teacherId, Address model) {
        if (teacherId == null) {
            throw new IllegalArgumentException("Cannot find a " + Teacher.class.getName() + " with a null id.");
        }
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Address.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Address.class.getName() + " which already has an ID.");
        }

        if (model.getType().getId() == null || !addressTypeRepository.exists(model.getType().getId())) {
            throw new AddressTypeResourceNotFoundException("No " + AddressType.class.getName() + " found for id :" + model.getType().getId());
        }

        model = addressManager.create(model);

        Teacher fetched = findOneById(teacherId);
        fetched.getContact().addAddress(model);
        teacherRepository.saveAndFlush(new TeacherDomain(fetched));
    }

    public void removeTeacherAddress(Long teacherId, Long addressId) {

    }

    public Email addEmail(Long teacherId, Email model) {
        return null;
    }

    public void removeTeacherEmail(Long teacherId, Long emailId) {

    }

    public Phone addPhone(Long teacherId, Phone model) {
        return null;
    }

    public void removeTeacherPhone(Long teacherId, Long phoneId) {

    }


}
