package org.ubp.ent.backend.core.controllers.teachingunit;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.teachingunit.TeachingUnitManager;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */

@RestController
@RequestMapping(TeachingUnitController.BASE_URL)
public class TeachingUnitController {

    public static final String BASE_URL = "/api/teachingunit";

    @Inject
    private TeachingUnitManager teachingUnitManager;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TeachingUnit> findAll() {
        return teachingUnitManager.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TeachingUnit findOneById(@PathVariable("id") Long id) {
        return teachingUnitManager.findOneById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TeachingUnit create(@RequestBody TeachingUnit teachingUnit) {
        return teachingUnitManager.create(teachingUnit);
    }

    @RequestMapping(value = "/{teachingUnitId}/module", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Module addModule(@PathVariable("teachingUnitId") Long teachingUnitId, @RequestBody Module module) {
        return teachingUnitManager.addModule(teachingUnitId, module);
    }
}
