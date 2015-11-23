package org.ubp.ent.backend.repository.classroom.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubp.ent.backend.domains.classroom.equipement.EquipmentTypeDomain;

/**
 * Created by Anthony on 23/11/2015.
 */
@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeDomain, Long> {
}
