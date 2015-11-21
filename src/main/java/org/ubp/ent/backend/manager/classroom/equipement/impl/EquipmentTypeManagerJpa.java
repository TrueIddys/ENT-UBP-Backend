package org.ubp.ent.backend.manager.classroom.equipement.impl;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.domains.equipements.EquipmentTypeDomain;
import org.ubp.ent.backend.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 21/11/2015.
 */
@Service
public class EquipmentTypeManagerJpa implements EquipmentTypeManager {

    @Inject
    private EntityManager entityManager;

    private String getEquipmentTypeClassName() {
        return EquipmentTypeDomain.class.getName();
    }

    @Override
    public EquipmentType create(EquipmentType equipmentType) {
        EquipmentTypeDomain domain = new EquipmentTypeDomain(equipmentType);

        entityManager.persist(domain);
        entityManager.flush();

        return domain.toModel();
    }

    //TODO : add a cache here with eviction on delete / update / create.
    @Override
    public List<EquipmentType> findAll() {
        List<EquipmentTypeDomain> domains = entityManager.createQuery("FROM " + getEquipmentTypeClassName(), EquipmentTypeDomain.class).getResultList();

        return domains.stream().map(EquipmentTypeDomain::toModel).collect(Collectors.toList());
    }


}
