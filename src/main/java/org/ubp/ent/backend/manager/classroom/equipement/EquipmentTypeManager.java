package org.ubp.ent.backend.manager.classroom.equipement;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.domains.classroom.equipement.EquipmentTypeDomain;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.repository.classroom.equipment.EquipmentTypeRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 21/11/2015.
 */
@Service
public class EquipmentTypeManager {

    @Inject
    EquipmentTypeRepository repository;

    @Inject
    private EntityManager entityManager;

    private String getEquipmentTypeClassName() {
        return EquipmentTypeDomain.class.getName();
    }

    public EquipmentType create(EquipmentType equipmentType) {
        if (Objects.isNull(equipmentType)) {
            throw new IllegalArgumentException("Cannot persists a null " + EquipmentType.class.getName());
        }
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        domain = repository.saveAndFlush(domain);

        return domain.toModel();
    }

    public List<EquipmentType> findAll() {
        List<EquipmentTypeDomain> domains = repository.findAll();

        return domains.stream().map(EquipmentTypeDomain::toModel).collect(Collectors.toList());
    }

}
