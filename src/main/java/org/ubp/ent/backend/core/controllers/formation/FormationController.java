package org.ubp.ent.backend.core.controllers.formation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.ubp.ent.backend.core.dao.manager.formation.FormationManager;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;

import javax.inject.Inject;

/**
 * Created by Anthony on 27/02/2016.
 */
@RestController
@RequestMapping(FormationController.BASE_URL)
public class FormationController {

    public static final String BASE_URL = "/api/formation";

    @Inject
    private FormationManager formationManager;


    @RequestMapping(value = "/root", method = RequestMethod.GET)
    public FormationComposite findRoot() {
        return formationManager.findRoot();
    }

    @RequestMapping(value = "/composite/{id}", method = RequestMethod.GET)
    public FormationComposite findCompositeById(@PathVariable("id") Long id) {
        return formationManager.findCompositeById(id);
    }

    @RequestMapping(value = "/leaf/{id}", method = RequestMethod.GET)
    public FormationLeaf findLeafById(@PathVariable("id") Long id) {
        return formationManager.findLeafById(id);
    }

    @RequestMapping(value = "/root", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormationComposite createRoot(@RequestBody FormationComposite root) {
        return formationManager.createRoot(root);
    }

    @RequestMapping(value = "/composite/create-for-parent/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormationComposite createComposite(@PathVariable("id") Long parentId, @RequestBody FormationComposite child) {
        return formationManager.createComposite(parentId, child);
    }

    @RequestMapping(value = "/leaf/create-for-parent/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public FormationLeaf createLeaf(@PathVariable("id") Long parentId, @RequestBody FormationLeaf child) {
        return formationManager.createLeaf(parentId, child);
    }


}
