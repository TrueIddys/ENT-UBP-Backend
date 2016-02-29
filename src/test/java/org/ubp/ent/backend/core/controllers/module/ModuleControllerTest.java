package org.ubp.ent.backend.core.controllers.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.dao.manager.course.CourseManager;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.core.model.module.Module;
import org.ubp.ent.backend.core.model.module.ModuleTest;
import org.ubp.ent.backend.core.model.type.ClassroomType;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Maxime on 28/02/2016.
 */
public class ModuleControllerTest extends WebIntegrationTest {

    private final String MODULE_BASE_URL = ModuleController.BASE_URL;

    @Inject
    private CourseManager courseManager;

    @Inject
    private ObjectMapper mapper;

    private Module createModule() throws Exception {
        return createModule(1).get(0);
    }

    private List<Module> createModule(int count) throws Exception {
        List<Module> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            Module model = ModuleTest.createOne("name " + i);
            String json = mapper.writeValueAsString(model);
            perform(post(MODULE_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(
                            result -> {
                                String response = result.getResponse().getContentAsString();
                                Module createdModule = mapper.readValue(response, Module.class);
                                created.add(createdModule);
                            }
                    );
        }
        return created;
    }




    @Test
    public void shouldFindModuleById() throws Exception {
        List<Module> models = createModule(5);

        for (Module model : models) {
            perform(get(MODULE_BASE_URL + "/" + model.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(model.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404NotFoundForNonExistingModule() throws Exception {
        perform(get(MODULE_BASE_URL + "/9945"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldFindAll() throws Exception {
        createModule(2);

        perform(get(MODULE_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(MODULE_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateIfIdIsAlreadyDefined() throws Exception {
        Module model = ModuleTest.createOne();
        model.setId(125L);

        perform(post(MODULE_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidModule() throws Exception {
        Module model = ModuleTest.createOne("Genie Log");
        String modelAsJson = mapper.writeValueAsString(model);

        perform(post(MODULE_BASE_URL).content(modelAsJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void shouldAddCourseToModule() throws Exception {
        Module model = createModule();

        Course course = CourseTest.createOne("Genie Log");
        course = courseManager.create(course);

        perform(post(MODULE_BASE_URL + "/" + model.getId() + "/course").content(mapper.writeValueAsString(course)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(course.getId().intValue())));
    }


}
