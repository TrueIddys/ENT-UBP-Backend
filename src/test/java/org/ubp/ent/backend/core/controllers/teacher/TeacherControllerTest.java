package org.ubp.ent.backend.core.controllers.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.model.teacher.Teacher;
import org.ubp.ent.backend.core.model.teacher.TeacherTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 10/02/2016.
 */
public class TeacherControllerTest extends WebIntegrationTest {

    private final String TEACHER_BASE_URL = TeacherController.BASE_URL;

    @Inject
    private ObjectMapper mapper;

    private Teacher createTeacher() throws Exception {
        return createTeachers(1).get(0);
    }

    private List<Teacher> createTeachers(int count) throws Exception {
        List<Teacher> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            Teacher model = TeacherTest.createOneEmpty();
            String json = mapper.writeValueAsString(model);
            perform(post(TEACHER_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(
                            result -> {
                                String response = result.getResponse().getContentAsString();
                                Teacher createdClassroom = mapper.readValue(response, Teacher.class);
                                created.add(createdClassroom);
                            }
                    );
        }
        return created;
    }

    @Test
    public void shouldFindAll() throws Exception {
        int count = 5;
        createTeachers(count);

        perform(get(TEACHER_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(count)));
    }

    @Test
    public void shouldFindOneById() throws Exception {
        List<Teacher> models = createTeachers(5);

        for (Teacher model : models) {
            perform(get(TEACHER_BASE_URL + "/" + model.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(model.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404FindOneByIdWithNonExistingId() throws Exception {
        perform(get(TEACHER_BASE_URL + "/2564"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateIfIdIsAlreadyDefined() throws Exception {
        Teacher model = TeacherTest.createOne();
        model.setId(125L);

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreate() throws Exception {
        Teacher model = TeacherTest.createOneEmpty();

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.type", is(model.getType().toString())));
        ;
    }

    @Test
    public void shouldCreateAddressEmailAndPhoneOnCascade() throws Exception {
        Teacher model = TeacherTest.createOne();
        //if one of below fails, the test makes no sense.
        assertThat(model.getContact().getAddresses()).isNotEmpty();
        assertThat(model.getContact().getEmails()).isNotEmpty();
        assertThat(model.getContact().getPhones()).isNotEmpty();

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andDo(
                        result -> {
                            String response = result.getResponse().getContentAsString();
                            Teacher createdModel = mapper.readValue(response, Teacher.class);
                            model.setId(createdModel.getId());
                        }
                );

        perform(get(TEACHER_BASE_URL + "/" + model.getId()))
                .andExpect(jsonPath("$.contact.addresses", hasSize(model.getContact().getAddresses().size())))
                .andExpect(jsonPath("$.contact.phones", hasSize(model.getContact().getPhones().size())))
                .andExpect(jsonPath("$.contact.emails", hasSize(model.getContact().getEmails().size())));
    }

}
