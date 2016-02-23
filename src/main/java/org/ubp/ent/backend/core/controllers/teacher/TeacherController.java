package org.ubp.ent.backend.core.controllers.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.teacher.TeacherManager;
import org.ubp.ent.backend.core.model.teacher.Teacher;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 10/02/2016.
 */
@RestController
@RequestMapping(TeacherController.BASE_URL)
public class TeacherController {

    public static final String BASE_URL = "/api/teacher";

    @Inject
    private TeacherManager teacherManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Teacher> findAll() {
        return teacherManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Teacher findOneById(@PathVariable("id") Long id) {
        return teacherManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher create(@RequestBody Teacher model) {
        return teacherManager.create(model);
    }

}
