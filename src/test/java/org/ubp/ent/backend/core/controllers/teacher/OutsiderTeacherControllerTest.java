package org.ubp.ent.backend.core.controllers.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacher;
import org.ubp.ent.backend.core.model.teacher.OutsiderTeacherTest;
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
public class OutsiderTeacherControllerTest extends WebIntegrationTest {

    private final String TEACHER_BASE_URL = OutsiderTeacherController.BASE_URL;

    @Inject
    private ObjectMapper mapper;

    private OutsiderTeacher createOutsiderTeacher() throws Exception {
        return createOutsiderTeachers(1).get(0);
    }

    private List<OutsiderTeacher> createOutsiderTeachers(int count) throws Exception {
        List<OutsiderTeacher> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            OutsiderTeacher model = OutsiderTeacherTest.createOneEmpty();
            String json = mapper.writeValueAsString(model);
            perform(post(TEACHER_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(
                            result -> {
                                String response = result.getResponse().getContentAsString();
                                OutsiderTeacher createdClassroom = mapper.readValue(response, OutsiderTeacher.class);
                                created.add(createdClassroom);
                            }
                    );
        }
        return created;
    }

    @Test
    public void shouldFindAll() throws Exception {
        int count = 5;
        createOutsiderTeachers(count);

        perform(get(TEACHER_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(count)));
    }

    @Test
    public void shouldFindOneById() throws Exception {
        List<OutsiderTeacher> models = createOutsiderTeachers(5);

        for (OutsiderTeacher model : models) {
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
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
        model.setId(125L);

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreate() throws Exception {
        OutsiderTeacher model = OutsiderTeacherTest.createOneEmpty();

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void shouldCreateAddressEmailAndPhoneOnCascade() throws Exception {
        OutsiderTeacher model = OutsiderTeacherTest.createOne();
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
                            OutsiderTeacher createdModel = mapper.readValue(response, OutsiderTeacher.class);
                            model.setId(createdModel.getId());
                        }
                );

        perform(get(TEACHER_BASE_URL + "/" + model.getId()))
                .andExpect(jsonPath("$.contact.addresses", hasSize(model.getContact().getAddresses().size())))
                .andExpect(jsonPath("$.contact.phones", hasSize(model.getContact().getPhones().size())))
                .andExpect(jsonPath("$.contact.emails", hasSize(model.getContact().getEmails().size())));
    }

}
