package org.ubp.ent.backend.core.controllers.classroom.equipment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
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
 * Created by Anthony on 02/12/2015.
 */
public class EquipmentTypeControllerTest extends WebIntegrationTest {

    private final String EQUIP_TYPE_BASE_URL = EquipmentTypeController.BASE_URL;

    @Inject
    private ObjectMapper mapper;

    private EquipmentType createEquipmentType() throws Exception {
        return createEquipmentType(1).get(0);
    }

    private List<EquipmentType> createEquipmentType(int count) throws Exception {
        List<EquipmentType> created = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            EquipmentType equipmentType = EquipmentTypeTest.createOne("name " + i);
            String json = mapper.writeValueAsString(equipmentType);
            perform(post(EQUIP_TYPE_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(result -> {
                        String response = result.getResponse().getContentAsString();
                        EquipmentType createdEquipmentTypes = mapper.readValue(response, EquipmentType.class);
                        created.add(createdEquipmentTypes);
                    });
        }
        return created;
    }

    @Test
    public void shouldFindEquipmentTypeById() throws Exception {
        List<EquipmentType> equipmentTypes = createEquipmentType(5);

        for (EquipmentType equipmentType : equipmentTypes) {
            perform(get(EQUIP_TYPE_BASE_URL + "/" + equipmentType.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(equipmentType.getId().intValue())));
        }
    }

    @Test
    public void shouldThrow404NotFoundForNonExistingEquipmentType() throws Exception {
        perform(get(EQUIP_TYPE_BASE_URL + "/9945"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldFindAll() throws Exception {
        createEquipmentType(2);

        perform(get(EQUIP_TYPE_BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldNotCreateWithNull() throws Exception {
        perform(post(EQUIP_TYPE_BASE_URL).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateWithValidEquipmentType() throws Exception {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        String json = mapper.writeValueAsString(equipmentType);

        perform(post(EQUIP_TYPE_BASE_URL).content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

}
