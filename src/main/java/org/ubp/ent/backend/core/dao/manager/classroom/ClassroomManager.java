package org.ubp.ent.backend.core.dao.manager.classroom;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.dao.repository.classroom.ClassroomRepository;
import org.ubp.ent.backend.core.dao.repository.classroom.equipment.RoomEquipmentRepository;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ClassroomResourceNotFoundException;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 20/11/2015.
 */
@Service
public class ClassroomManager {

    @Inject
    private ClassroomRepository classroomRepository;

    @Inject
    private RoomEquipmentRepository roomEquipmentRepository;

    @Inject
    private EquipmentTypeManager equipmentTypeManager;

    public Classroom create(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Classroom.class.getName());
        }
        if (classroom.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + Classroom.class.getName() + " which already has an ID.");
        }

        ClassroomDomain domain = new ClassroomDomain(classroom);
        domain = classroomRepository.saveAndFlush(domain);
        classroom.setId(domain.getId());

        return domain.toModel();
    }

    public Classroom findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Classroom.class.getName() + " with a null id.");
        }
        ClassroomDomain domain = classroomRepository.findOne(id);

        if (domain == null) {
            throw new ClassroomResourceNotFoundException("No " + Classroom.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<Classroom> findAll() {
        List<ClassroomDomain> domains = classroomRepository.findAll();

        return domains.stream().map(ClassroomDomain::toModel).collect(Collectors.toList());
    }

    public Classroom findOneByIdJoiningEquipments(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Classroom.class.getName() + " with a null id.");
        }
        ClassroomDomain domain = classroomRepository.findOneByIdJoiningEquipments(id);

        if (domain == null) {
            throw new ClassroomResourceNotFoundException("No " + Classroom.class.getName() + " found for id :" + id);
        }

        Classroom model = domain.toModel();
        domain.getEquipments().forEach(e -> model.addEquipment(e.toModel()));

        return model;
    }


    public List<Classroom> findAllJoiningEquipments() {
        List<ClassroomDomain> domains = classroomRepository.findJoiningEquipments();

        return domains.parallelStream().map(domain -> {
            Classroom model = domain.toModel();
            domain.getEquipments().forEach(e -> model.addEquipment(e.toModel()));

            return model;
        }).collect(Collectors.toList());
    }

    public RoomEquipment addEquipment(Long classroomId, Long equipmentTypeId, Quantity quantity) {
        if (classroomId == null) {
            throw new IllegalArgumentException("Cannot add an equipment to a " + Classroom.class.getName() + " which has a null id.");
        }
        if (equipmentTypeId == null) {
            throw new IllegalArgumentException("Cannot add an equipment to a " + Classroom.class.getName() + " if " + EquipmentType.class.getName() + " ID is null.");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Cannot add a equipment to a " + Classroom.class.getName() + " with a null quantity.");
        }

        Classroom classroom = findOneById(classroomId);
        EquipmentType equipmentType = equipmentTypeManager.findOneById(equipmentTypeId);
        RoomEquipment roomEquipment = new RoomEquipment(equipmentType, quantity);

        RoomEquipmentDomain roomEquipmentDomain = new RoomEquipmentDomain(roomEquipment, new ClassroomDomain(classroom));
        if (roomEquipmentRepository.exists(roomEquipmentDomain.getId())) {
            // Because it makes no sense for a classroom to having same equipment twice the same, increment quantity instead.
            throw new ModelConstraintViolationException("Cannot add a second " + RoomEquipment.class.getName() + " to the classroom, there can't be two RoomEquipment with the same EquipmentType for a Classroom.");
        }

        roomEquipmentDomain = roomEquipmentRepository.saveAndFlush(roomEquipmentDomain);

        return roomEquipmentDomain.toModel();
    }

}
