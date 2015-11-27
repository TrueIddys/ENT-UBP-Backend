package org.ubp.ent.backend.core.controllers.classroom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ubp.ent.backend.core.dao.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.core.model.classroom.Classroom;

import javax.inject.Inject;

/**
 * Created by Anthony on 27/11/2015.
 */
@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Inject
    private ClassroomManager classroomManager;

    @RequestMapping(method = RequestMethod.POST)
    public Classroom create(Classroom classroom) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot create a null " + Classroom.class.getName(), new NullPointerException());
        }

        classroom = classroomManager.create(classroom);

        return classroom;
    }

}
