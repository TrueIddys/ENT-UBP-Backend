package org.ubp.ent.backend.core.controllers.formation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationCompositeTest;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;
import org.ubp.ent.backend.core.model.formation.FormationLeafTest;
import org.ubp.ent.backend.utils.TestScenarioHelper;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 27/02/2016.
 */
public class FormationControllerTest extends WebIntegrationTest {

    private final String FORMATION_BASE_URL = FormationController.BASE_URL;

    @Inject
    private ObjectMapper mapper;

    @Inject
    private TestScenarioHelper helper;

    @Test
    public void shouldReturnNotFoundFindRootIfNoRootIsPresentInDatabase() throws Exception {
        perform(get(FORMATION_BASE_URL + "/root"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindRoot() throws Exception {
        FormationComposite root = helper.createFormationTree();

        perform(get(FORMATION_BASE_URL + "/root"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(root.getId().intValue())));
    }

    @Test
    public void shouldReturnNotFoundFindCompositeByIdWithNonExisting() throws Exception {
        helper.createFormationTree();

        perform(get(FORMATION_BASE_URL + "/composite/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindCompositeById() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        root.addFormation(child);
        root = helper.createFormationTree(root);
        child = (FormationComposite) root.getFormations().get(0);

        perform(get(FORMATION_BASE_URL + "/composite/" + child.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(child.getId().intValue())));
    }

    @Test
    public void shouldReturnNotFoundFindLeafByIdWithNonExisting() throws Exception {
        helper.createFormationTree();

        perform(get(FORMATION_BASE_URL + "/leaf/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindLeafById() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        root.addFormation(child);
        root = helper.createFormationTree(root);
        child = (FormationLeaf) root.getFormations().get(0);

        FormationLeaf leaf = (FormationLeaf) root.getFormations().get(0);
        perform(get(FORMATION_BASE_URL + "/leaf/" + leaf.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(leaf.getId().intValue())));
    }

    @Test
    public void shouldFailCreateRootIfAlreadyDefined() throws Exception {
        helper.createFormationTree();

        perform(post(FORMATION_BASE_URL + "/root").content(mapper.writeValueAsString(FormationCompositeTest.createOneEmpty())).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateRoot() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();


        perform(post(FORMATION_BASE_URL + "/root").content(mapper.writeValueAsString(root)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(root.getName())));
    }

    @Test
    public void shouldCreateOnCascade() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        root.addFormation(child);
        perform(post(FORMATION_BASE_URL + "/root").content(mapper.writeValueAsString(root)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(root.getName())))
                .andExpect(jsonPath("$.formations", hasSize(1)))
                .andExpect(jsonPath("$.formations[0].id", notNullValue()))
                .andExpect(jsonPath("$.formations[0].name", is(child.getName())));

    }

    @Test
    public void shouldFailCreateCompositeIfParentDoesNotExists() throws Exception {
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        perform(post(FORMATION_BASE_URL + "/composite/create-for-parent/99999").content(mapper.writeValueAsString(child)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateComposite() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = helper.createFormationTree(root);

        FormationComposite child = FormationCompositeTest.createOneEmpty();
        perform(post(FORMATION_BASE_URL + "/composite/create-for-parent/" + root.getId()).content(mapper.writeValueAsString(child)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(child.getName())));
    }

    @Test
    public void shouldFailCreateLeafIfParentDoesNotExists() throws Exception {
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        perform(post(FORMATION_BASE_URL + "/leaf/create-for-parent/99999").content(mapper.writeValueAsString(child)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateLeaf() throws Exception {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = helper.createFormationTree(root);

        FormationLeaf child = FormationLeafTest.createOneEmpty();
        perform(post(FORMATION_BASE_URL + "/leaf/create-for-parent/" + root.getId()).content(mapper.writeValueAsString(child)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(child.getName())));
    }

}
