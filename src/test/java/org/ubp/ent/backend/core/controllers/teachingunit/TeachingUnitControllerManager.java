package org.ubp.ent.backend.core.controllers.teachingunit;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.controllers.course.CourseController;
import org.ubp.ent.backend.core.dao.manager.module.ModuleManager;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.module.ModuleTest;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnitTest;
import org.ubp.ent.backend.utils.WebApplicationTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Maxime on 28/02/2016.
 */
public class TeachingUnitControllerManager extends WebIntegrationTest {

    private final String TEACHINGUNIT_BASE_URL = TeachingUnitController.BASE_URL;

    @Inject
    private ModuleManager moduleManager;

    @Inject
    private ObjectMapper mapper;

    private TeachingUnit createTeachingUnit() throws Exception {
        return createTeachingUnit(1).get(0);
    }

    private List<TeachingUnit> createTeachingUnit(int count) throws Exception {
        List<TeachingUnit> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            TeachingUnit model = TeachingUnitTest.createOne("name " + i);
            String json = mapper.writeValueAsString(model);
            perform(post(TEACHINGUNIT_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(
                            result -> {
                                String response = result.getResponse().getContentAsString();
                                TeachingUnit createdTeachingUnit = mapper.readValue(response, TeachingUnit.class);
                                created.add(createdTeachingUnit);
                            }
                    );
        }
        return created;
    }


    @Test
    public void shouldFindTeachingUnitById() throws Exception {
        List<TeachingUnit> models = createTeachingUnit(5);

        for (TeachingUnit model : models) {
            perform(get(TEACHINGUNIT_BASE_URL + "/" + model.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(model.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404NotFoundForNonExistingTeachingUnit() throws Exception {
        perform(get(TEACHINGUNIT_BASE_URL + "/9945"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldFindAll() throws Exception {
        createTeachingUnit(2);

        perform(get(TEACHINGUNIT_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(TEACHINGUNIT_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateIfIdIsAlreadyDefined() throws Exception {
        TeachingUnit model = TeachingUnitTest.createOne();
        model.setId(125L);

        perform(post(TEACHINGUNIT_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidTeachingUnit() throws Exception {
        TeachingUnit model = TeachingUnitTest.createOne("Compilation");
        String modelAsJson = mapper.writeValueAsString(model);

        perform(post(TEACHINGUNIT_BASE_URL).content(modelAsJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void shouldAddCourseToModule() throws Exception {
        TeachingUnit model = createTeachingUnit();

        Module module = ModuleTest.createOne("Genie Log");
        module = moduleManager.create(module);

        perform(post(TEACHINGUNIT_BASE_URL + "/" + model.getId() + "/module").content(mapper.writeValueAsString(module)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(module.getId().intValue())));
    }
}
