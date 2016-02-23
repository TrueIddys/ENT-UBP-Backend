package org.ubp.ent.backend.core.dao.repository.classroom.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomain;
import org.ubp.ent.backend.core.domains.classroom.equipement.RoomEquipmentDomainId;

/**
 * Created by Anthony on 29/11/2015.
 */
public interface RoomEquipmentRepository extends JpaRepository<RoomEquipmentDomain, RoomEquipmentDomainId> {

}
