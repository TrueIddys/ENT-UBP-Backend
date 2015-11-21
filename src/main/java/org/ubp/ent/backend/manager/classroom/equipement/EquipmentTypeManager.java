package org.ubp.ent.backend.manager.classroom.equipement;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;

import java.util.List;

/**
 * Created by Anthony on 21/11/2015.
 */
public interface EquipmentTypeManager {

    EquipmentType create(EquipmentType equipmentType);

    List<EquipmentType> findAll();

}
