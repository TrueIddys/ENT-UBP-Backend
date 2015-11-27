package org.ubp.ent.backend.core.controllers.classroom;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.core.model.classroom.Classroom;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 27/11/2015.
 */
@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Inject
    private ClassroomManager classroomManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Classroom> findAll() {
        return classroomManager.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Classroom create(@RequestBody Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot create a null " + Classroom.class.getName(), new NullPointerException());
        }

        return classroomManager.create(classroom);
    }


}
