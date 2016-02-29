package org.ubp.ent.backend.core.controllers.module;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.module.ModuleManager;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.type.ClassroomType;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */

@RestController
@RequestMapping(ModuleController.BASE_URL)
public class ModuleController {

    public static final String BASE_URL = "/api/module";

    @Inject
    private ModuleManager moduleManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Module> findAll() {
        return moduleManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Module findOneById(@PathVariable("id") Long id) {
        return moduleManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Module create(@RequestBody Module classroom) {
        return moduleManager.create(classroom);
    }

    @RequestMapping(value = "/{moduleId}/course", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@PathVariable("moduleId") Long moduleId, @RequestBody Course course) {
        return moduleManager.addCourse(moduleId, course);
    }

}
