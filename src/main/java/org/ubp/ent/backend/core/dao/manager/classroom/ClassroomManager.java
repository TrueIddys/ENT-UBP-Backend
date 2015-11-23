package org.ubp.ent.backend.core.dao.manager.classroom;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.classroom.ClassroomRepository;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.model.classroom.Classroom;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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

        return domain.toModel();
    }

    public List<Classroom> findAll() {
        List<ClassroomDomain> domains = repository.findAll();

        return domains.stream().map(ClassroomDomain::toModel).collect(Collectors.toList());
    }


}
