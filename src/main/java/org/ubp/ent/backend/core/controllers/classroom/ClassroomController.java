package org.ubp.ent.backend.core.controllers.classroom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 27/11/2015.
 */
@RestController
@RequestMapping(ClassroomController.BASE_URL)
public class ClassroomController {

    public static final String BASE_URL = "/api/classroom";

    @Inject
    private ClassroomManager classroomManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Classroom> findAll() {
        return classroomManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Classroom findOneById(@PathVariable("id") Long id) {
        return classroomManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Classroom create(@RequestBody Classroom classroom) {
        return classroomManager.create(classroom);
    }

    @RequestMapping(value = "/{classroomId}/equipment-type/{equipmentTypeId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RoomEquipment addEquipment(@PathVariable("classroomId") Long classroomId,
                                      @PathVariable("equipmentTypeId") Long equipmentTypeId,
                                      @RequestParam("quantity") Integer quantity) {
        return classroomManager.addEquipment(classroomId, equipmentTypeId, new Quantity(quantity));
    }

}
