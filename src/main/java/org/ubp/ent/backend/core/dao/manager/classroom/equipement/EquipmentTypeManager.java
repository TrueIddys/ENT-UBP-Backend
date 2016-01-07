package org.ubp.ent.backend.core.dao.manager.classroom.equipement;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.classroom.equipment.EquipmentTypeRepository;
import org.ubp.ent.backend.core.domains.classroom.equipement.EquipmentTypeDomain;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EquipmentTypeResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedClass;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 21/11/2015.
 */
@Service
public class EquipmentTypeManager {

    @Inject
    private EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentType create(EquipmentType equipmentType) {
        if (equipmentType == null) {
            throw new IllegalArgumentException("Cannot persists a null " + EquipmentType.class.getName());
        }
        if (equipmentType.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedClass("Cannot persist a " + EquipmentType.class.getName() + " which already has an ID.");
        }
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        domain = equipmentTypeRepository.saveAndFlush(domain);
        equipmentType.setId(domain.getId());

        return domain.toModel();
    }

    public List<EquipmentType> findAll() {
        List<EquipmentTypeDomain> domains = equipmentTypeRepository.findAll();

        return domains.stream().map(EquipmentTypeDomain::toModel).collect(Collectors.toList());
    }

    public EquipmentType findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + EquipmentType.class.getName() + " with a null id.");
        }
        EquipmentTypeDomain domain = equipmentTypeRepository.findOne(id);

        if (domain == null) {
            throw new EquipmentTypeResourceNotFoundException("No " + EquipmentType.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }
}
