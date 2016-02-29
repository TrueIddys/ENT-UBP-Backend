package org.ubp.ent.backend.core.dao.manager.teachingunit;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.teachingunit.TeachingUnitRepository;
import org.ubp.ent.backend.core.domains.teachingunit.TeachingUnitDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Maxime on 28/02/2016.
 */

@Service
public class TeachingUnitManager {

    @Inject
    private TeachingUnitRepository teachingUnitRepository;

    public TeachingUnit create(TeachingUnit teachingUnit) {
        if (teachingUnit == null) {
            throw new IllegalArgumentException("Cannot persist a null " + TeachingUnit.class.getName());
        }
        if (teachingUnit.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + TeachingUnit.class.getName() + " which already has an ID.");
        }

        TeachingUnitDomain domain = new TeachingUnitDomain(teachingUnit);
        domain = teachingUnitRepository.saveAndFlush(domain);
        teachingUnit.setId(domain.getId());

        return domain.toModel();
    }

    public TeachingUnit findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + TeachingUnit.class.getName() + " with a null id.");
        }
        TeachingUnitDomain domain = teachingUnitRepository.findOne(id);

        if (domain == null) {
            throw new CourseResourceNotFoundException("No " + TeachingUnit.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<TeachingUnit> findAll() {
        List<TeachingUnitDomain> domains = teachingUnitRepository.findAll();

        return domains.stream().map(TeachingUnitDomain::toModel).collect(Collectors.toList());
    }

}
