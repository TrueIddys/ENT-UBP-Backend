package org.ubp.ent.backend.core.dao.manager.teachingunit;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.module.ModuleRepository;
import org.ubp.ent.backend.core.dao.repository.teachingunit.TeachingUnitRepository;
import org.ubp.ent.backend.core.domains.module.ModuleDomain;
import org.ubp.ent.backend.core.domains.teachingunit.TeachingUnitDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.CourseResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.TeachingUnitResourceNotFoundException;
import org.ubp.ent.backend.core.model.module.Module;
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

    @Inject
    private ModuleRepository moduleRepository;

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

    public TeachingUnit findOneByIdJoiningModules(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + TeachingUnit.class.getName() + " with a null id.");
        }
        TeachingUnitDomain domain = teachingUnitRepository.findOneByIdJoiningModules(id);

        if (domain == null) {
            throw new TeachingUnitResourceNotFoundException("No " + TeachingUnit.class.getName() + " found for id :" + id);
        }

        TeachingUnit model = domain.toModel();
        domain.getModules().forEach(e -> model.addModule(e.toModel()));

        return model;
    }


    public List<TeachingUnit> findAllJoiningModules() {
        List<TeachingUnitDomain> domains = teachingUnitRepository.findJoiningModules();

        return domains.parallelStream().map(
                domain -> {
                    TeachingUnit model = domain.toModel();
                    domain.getModules().forEach(e -> model.addModule(e.toModel()));

                    return model;
                }
        ).collect(Collectors.toList());
    }

    public Module addModule(Long teachingUnitId, Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Cannot add a null module to a " + TeachingUnit.class.getName());
        }
        if (teachingUnitId == null){
            throw new IllegalArgumentException("Cannot add a module to a " + TeachingUnit.class.getName() + "who has a null id");
        }

        TeachingUnit teachingUnit = findOneById(teachingUnitId);

        ModuleDomain moduleDomain = new ModuleDomain(module);
        moduleDomain= moduleRepository.saveAndFlush(moduleDomain);

        TeachingUnitDomain teachingUnitDomain = new TeachingUnitDomain(teachingUnit);
        teachingUnitDomain.addModule(moduleDomain);
        teachingUnitRepository.saveAndFlush(teachingUnitDomain);
        module.setId(moduleDomain.getId());

        return moduleDomain.toModel();
    }

}
