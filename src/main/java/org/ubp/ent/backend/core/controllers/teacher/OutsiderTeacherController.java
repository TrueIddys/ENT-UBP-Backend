package org.ubp.ent.backend.core.controllers.teacher;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.teacher.OutsiderTeacherManager;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;

import javax.inject.Inject;

/**
 * Created by Anthony on 10/02/2016.
 */
@RestController
@RequestMapping(OutsiderTeacherController.BASE_URL)
public class OutsiderTeacherController {

    public static final String BASE_URL = "/api/outsider-teacher";

    @Inject
    private OutsiderTeacherManager outsiderTeacherManager;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OutsiderTeacher create(@RequestBody OutsiderTeacher model) {
        return outsiderTeacherManager.create(model);
    }

}
