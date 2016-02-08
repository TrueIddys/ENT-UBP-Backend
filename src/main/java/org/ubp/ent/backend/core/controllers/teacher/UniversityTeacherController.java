package org.ubp.ent.backend.core.controllers.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.teacher.UniversityTeacherManager;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Anthony on 08/02/2016.
 */
@RestController
@RequestMapping(UniversityTeacherController.BASE_URL)
public class UniversityTeacherController {

    public static final String BASE_URL = "/api/university-teacher";

    @Inject
    private UniversityTeacherManager universityTeacherManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UniversityTeacher> findAll() {
        return universityTeacherManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UniversityTeacher findOneById(@PathVariable("id") Long id) {
        return universityTeacherManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UniversityTeacher create(@RequestBody UniversityTeacher teacher) {
        return universityTeacherManager.create(teacher);
    }

}
