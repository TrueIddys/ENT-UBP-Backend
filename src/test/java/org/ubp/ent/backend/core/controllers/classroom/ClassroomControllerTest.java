package org.ubp.ent.backend.core.controllers.classroom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anthony on 27/11/2015.
 */
public class ClassroomControllerTest extends WebIntegrationTest {

    private final String CLASSROOM_BASE_URL = ClassroomController.BASE_URL;

    @Inject
    private EquipmentTypeManager equipmentTypeManager;

    @Inject
    private ObjectMapper mapper;

    private Classroom createClassroom() throws Exception {
        return createClassroom(1).get(0);
    }

    private List<Classroom> createClassroom(int count) throws Exception {
        List<Classroom> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            Classroom model = ClassroomTest.createOne("name " + i);
            String json = mapper.writeValueAsString(model);
            perform(post(CLASSROOM_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(result -> {
                        String response = result.getResponse().getContentAsString();
                        Classroom createdClassroom = mapper.readValue(response, Classroom.class);
                        created.add(createdClassroom);
                    });
        }
        return created;
    }


    @Test
    public void shouldFindClassroomById() throws Exception {
        List<Classroom> models = createClassroom(5);

        for (Classroom model : models) {
            perform(get(CLASSROOM_BASE_URL + "/" + model.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(model.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404NotFoundForNonExistingClassroom() throws Exception {
        perform(get(CLASSROOM_BASE_URL + "/9945"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldFindAll() throws Exception {
        createClassroom(2);

        perform(get(CLASSROOM_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(CLASSROOM_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateIfIdIsAlreadyDefined() throws Exception {
        Classroom model = ClassroomTest.createOne();
        model.setId(125L);

        perform(post(CLASSROOM_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(model)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidClassroom() throws Exception {
        Classroom model = ClassroomTest.createOne("3005");
        String modelAsJson = mapper.writeValueAsString(model);

        perform(post(CLASSROOM_BASE_URL).content(modelAsJson).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    public void shouldAddRoomEquipmentToClassroom() throws Exception {
        Classroom model = createClassroom();

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        int quantity = 12;
        perform(post(CLASSROOM_BASE_URL + "/" + model.getId() + "/equipment-type/" + equipmentType.getId()).param("quantity", String.valueOf(quantity)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.equipmentType.id", is(equipmentType.getId().intValue())))
                .andExpect(jsonPath("$.quantity.maxQuantity", is(quantity)));
    }


    @Test
    public void shouldNotAddTwoRoomEquipmentWithTheSameEquipmentTypeToAClassroom() throws Exception {
        Classroom model = createClassroom();

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        perform(post(CLASSROOM_BASE_URL + "/" + model.getId() + "/equipment-type/" + equipmentType.getId()).param("quantity", "12"))
                .andExpect(status().isCreated());

        perform(post(CLASSROOM_BASE_URL + "/" + model.getId() + "/equipment-type/" + equipmentType.getId()).param("quantity", "12"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAddRoomEquipmentIfClassroomDoesNotExists() throws Exception {
        Classroom classroom = ClassroomTest.createOne("SL6");
        classroom.setId(26L);

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        perform(post(CLASSROOM_BASE_URL + "/" + classroom.getId() + "/equipment-type/" + equipmentType.getId()).param("quantity", "12"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotAddRoomEquipmentIfEquipmentTypeDoesNotExists() throws Exception {
        Classroom model = createClassroom();

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType.setId(256L);

        perform(post(CLASSROOM_BASE_URL + "/" + model.getId() + "/equipment-type/" + equipmentType.getId()).param("quantity", "12"))
                .andExpect(status().isNotFound());
    }

}
