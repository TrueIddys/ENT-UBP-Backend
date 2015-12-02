package org.ubp.ent.backend.core.controllers.classroom.equipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 02/12/2015.
 */
@RestController
@RequestMapping("/equipment-type")
public class EquipmentTypeController {

    @Inject
    private EquipmentTypeManager equipmentTypeManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<EquipmentType> findAll() {
        return equipmentTypeManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public EquipmentType findOneById(@PathVariable("id") Long id) {
        return equipmentTypeManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentType create(@RequestBody EquipmentType equipmentType) {
        if (equipmentType == null) {
            throw new IllegalArgumentException("Cannot create a null " + EquipmentType.class.getName(), new NullPointerException());
        }

        return equipmentTypeManager.create(equipmentType);
    }

}
