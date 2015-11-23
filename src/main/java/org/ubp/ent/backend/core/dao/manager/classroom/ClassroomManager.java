package org.ubp.ent.backend.core.dao.manager.classroom;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.classroom.ClassroomRepository;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 20/11/2015.
 */
@Service
public class ClassroomManager {

    @Inject
    private EntityManager entityManager;

    @Inject
    private ClassroomRepository repository;

    public Classroom create(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot persist a null " + Classroom.class.getName());
        }

        ClassroomDomain domain = new ClassroomDomain(classroom);
        domain = repository.saveAndFlush(domain);
        classroom.setId(domain.getId());

        return domain.toModel();
    }


    public Classroom findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + Classroom.class.getName() + " with a null id.");
        }
        ClassroomDomain domain = repository.findOne(id);

        if (domain == null) {
            throw  new IllegalArgumentException("No " + Classroom.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<Classroom> findAll() {
        List<ClassroomDomain> domains = repository.findAll();

        return domains.stream().map(ClassroomDomain::toModel).collect(Collectors.toList());
    }

    @Transactional
    public RoomEquipment addEquipment(Classroom classroom, RoomEquipment roomEquipment) {
        if (classroom.getId() == null) {
            throw new IllegalArgumentException("Cannot add an equipment to a " + Classroom.class.getName() + " which has a null id.");
        }
        if (roomEquipment.getEquipmentType() == null || roomEquipment.getEquipmentType().getId() == null) {
            throw new IllegalArgumentException("Cannot add an equipment with an " + EquipmentType.class.getName() + " null or without id.");
        }
        ClassroomDomain classroomDomain = new ClassroomDomain(classroom);
        RoomEquipmentDomain roomEquipmentDomain = new RoomEquipmentDomain(roomEquipment, classroomDomain);

        entityManager.persist(roomEquipmentDomain);
        entityManager.flush();

        return roomEquipmentDomain.toModel();
    }
}
