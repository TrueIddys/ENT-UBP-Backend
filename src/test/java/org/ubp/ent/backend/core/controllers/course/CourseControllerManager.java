package org.ubp.ent.backend.core.controllers.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.core.model.course.Course;
import org.ubp.ent.backend.core.model.course.CourseTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Maxime on 15/02/2016.
 */
public class CourseControllerManager extends WebIntegrationTest {

    private final String COURSE_BASE_URL = CourseController.BASE_URL;

    @Inject
    private ObjectMapper mapper;

    private Course createCourse() throws Exception {
        return createCourse(1).get(0);
    }

    private List<Course> createCourse(int count) throws Exception {
        List<Course> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            Course model = CourseTest.createOne("name " + i);
            String json = mapper.writeValueAsString(model);
            perform(post(COURSE_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(result -> {
                        String response = result.getResponse().getContentAsString();
                        Course createdCourse = mapper.readValue(response, Course.class);
                        created.add(createdCourse);
                    });
        }
        return created;
    }


    @Test
    public void shouldFindCourseById() throws Exception {
        List<Course> models = createCourse(5);

        for (Course model : models) {
            perform(get(COURSE_BASE_URL + "/" + model.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(model.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404NotFoundForNonExistingCourse() throws Exception {
        perform(get(COURSE_BASE_URL + "/9945"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldFindAll() throws Exception {
        createCourse(2);

        perform(get(COURSE_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(COURSE_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateIfIdIsAlreadyDefined() throws Exception {
        Course model = CourseTest.createOneEmpty();
        model.setId(125L);

        perform(post(COURSE_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidCourse() throws Exception {
        Course model = CourseTest.createOne("Compilation");
        String modelAsJson = mapper.writeValueAsString(model);

        perform(post(COURSE_BASE_URL).content(modelAsJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }
}
