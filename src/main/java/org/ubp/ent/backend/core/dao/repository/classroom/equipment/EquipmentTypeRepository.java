package org.ubp.ent.backend.core.dao.repository.classroom.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.classroom.equipement.EquipmentTypeDomain;

/**
 * Created by Anthony on 23/11/2015.
 */
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeDomain, Long> {

}
