package org.ubp.ent.backend.core.controllers.classroom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 27/11/2015.
 */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClassroomControllerTest extends WebIntegrationTest {

    @Inject
    private ObjectMapper mapper;

    private void createClassroom(int count) throws Exception {
        for (int i = 0; i < count; ++i) {
            Classroom classroom = ClassroomTest.createOne("name " + i);
            String classroomJson = mapper.writeValueAsString(classroom);
            perform(post("/classroom").content(classroomJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated());
        }
    }

    @Test
    public void shouldFindAll() throws Exception {
        createClassroom(2);

        perform(get("/classroom"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post("/classroom").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidClassroom() throws Exception {
        Classroom classroom = ClassroomTest.createOne("3005");
        String classroomJson = mapper.writeValueAsString(classroom);

        perform(post("/classroom").content(classroomJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

}