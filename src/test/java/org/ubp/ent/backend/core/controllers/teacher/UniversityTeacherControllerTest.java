package org.ubp.ent.backend.core.controllers.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.address.AddressTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.email.EmailTypeManager;
import org.ubp.ent.backend.core.dao.manager.teacher.contact.phone.PhoneTypeManager;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacher;
import org.ubp.ent.backend.core.model.teacher.UniversityTeacherTest;
import org.ubp.ent.backend.core.model.teacher.contact.address.Address;
import org.ubp.ent.backend.core.model.teacher.contact.email.Email;
import org.ubp.ent.backend.core.model.teacher.contact.phone.Phone;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 08/02/2016.
 */
public class UniversityTeacherControllerTest extends WebIntegrationTest {

    private final String TEACHER_BASE_URL = UniversityTeacherController.BASE_URL;

    @Inject
    private PhoneTypeManager phoneTypeManager;

    @Inject
    private AddressTypeManager addressTypeManager;

    @Inject
    private EmailTypeManager emailTypeManager;

    @Inject
    private ObjectMapper mapper;

    private UniversityTeacher createUniversityTeacher() throws Exception {
        return createUniversityTeachers(1).get(0);
    }

    private List<UniversityTeacher> createUniversityTeachers(int count) throws Exception {
        List<UniversityTeacher> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            UniversityTeacher model = UniversityTeacherTest.createOneEmpty();
            String json = mapper.writeValueAsString(model);
            perform(post(TEACHER_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(result -> {
                        String response = result.getResponse().getContentAsString();
                        UniversityTeacher createdClassroom = mapper.readValue(response, UniversityTeacher.class);
                        created.add(createdClassroom);
                    });
        }
        return created;
    }

    @Test
    public void shouldFindAll() throws Exception {
        int count = 5;
        createUniversityTeachers(count);

        perform(get(TEACHER_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(count)));
    }

    @Test
    public void shouldFindOneById() throws Exception {
        List<UniversityTeacher> models = createUniversityTeachers(5);

        for (UniversityTeacher model : models) {
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
        UniversityTeacher model = UniversityTeacherTest.createOne();
        model.setId(125L);

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreate() throws Exception {
        UniversityTeacher model = UniversityTeacherTest.createOneEmpty();

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void shouldCreateAddressEmailAndPhoneOnCascade() throws Exception {
        UniversityTeacher model = UniversityTeacherTest.createOne();
        //if one of below fails, the test makes no sense.
        assertThat(model.getContact().getAddresses()).isNotEmpty();
        assertThat(model.getContact().getEmails()).isNotEmpty();
        assertThat(model.getContact().getPhones()).isNotEmpty();

        model.getContact().getAddresses().stream()
                .map(Address::getType).forEach(addressTypeManager::create);
        model.getContact().getEmails().stream()
                .map(Email::getType).forEach(emailTypeManager::create);
        model.getContact().getPhones().stream()
                .map(Phone::getType).forEach(phoneTypeManager::create);

        perform(post(TEACHER_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    UniversityTeacher createdModel = mapper.readValue(response, UniversityTeacher.class);
                    model.setId(createdModel.getId());
                });


        perform(get(TEACHER_BASE_URL + "/" + model.getId()))
                .andExpect(jsonPath("$.contact.addresses", hasSize(model.getContact().getAddresses().size())))
                .andExpect(jsonPath("$.contact.phones", hasSize(model.getContact().getPhones().size())))
                .andExpect(jsonPath("$.contact.emails", hasSize(model.getContact().getEmails().size())));
    }

}
