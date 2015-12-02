package org.ubp.ent.backend.core.dao.manager.classroom;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.exceptions.ClassroomNotFoundException;
import org.ubp.ent.backend.core.exceptions.EquipmentTypeNotFoundException;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


/**
 * Created by Anthony on 23/11/2015.
 */
public class ClassroomManagerTest extends WebApplicationTest {

    @Inject
    private ClassroomManager classroomManager;

    @Inject
    private EquipmentTypeManager equipmentTypeManager;


    @Test
    public void shouldFindOneById() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        assertThat(classroomManager.findOneById(model.getId())).isEqualTo(model);
    }

    @Test
    public void shouldFailFindOneByIdWithNull() {
        try {
            classroomManager.findOneById(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldFailFindByNonExistingId() {
        try {
            classroomManager.findOneById(205L);
            fail();
        } catch (ClassroomNotFoundException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldCreate() {
        Classroom model = ClassroomTest.createOne("SL4");
        classroomManager.create(model);

        assertThat(classroomManager.findAll()).hasSize(1);

        Classroom model2 = ClassroomTest.createOne("SL5");
        classroomManager.create(model2);

        assertThat(classroomManager.findAll()).hasSize(2);
    }

    @Test
    public void shouldSetIdOnReference() {
        Classroom model = ClassroomTest.createOne("SL5");

        classroomManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test
    public void shouldFailCreateTwoClassroomWithTheSameName() {
        try {
            Classroom model = ClassroomTest.createOne();
            classroomManager.create(model);

            Classroom model2 = ClassroomTest.createOne();
            classroomManager.create(model2);
            fail();
        } catch (DataIntegrityViolationException e) {
            assertThat(e.getCause()).isOfAnyClassIn(ConstraintViolationException.class);
        }
    }

    @Test
    public void shouldFailCreateWithNull() {
        try {
            classroomManager.create(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldFailCreateIfIdIsDefined() {
        Classroom model = ClassroomTest.createOne();
        model.setId(25L);
        try {
            classroomManager.create(model);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotCreateEquipmentByClassroomOnCascade() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        Classroom model = ClassroomTest.createOne("SCI_3006");
        model.addEquipment(new RoomEquipment(equipmentType, new Quantity(12)));
        classroomManager.create(model);

        Classroom modelDB = classroomManager.findAll().get(0);

        assertThat(modelDB.getEquipments()).hasSize(0);
    }

    @Test
    public void shouldAddEquipmentToClassroom() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        classroomManager.addEquipment(model.getId(), equipmentType.getId(), new Quantity(12));

        assertThat(equipmentTypeManager.findAll()).hasSize(1);
        assertThat(classroomManager.findAll().get(0).getEquipments()).hasSize(1);
        assertThat(classroomManager.findAll().get(0).getEquipments().iterator().next().getEquipmentType().getName()).isEqualTo("Computer");
    }

    @Test
    public void shouldNotAddEquipmentToClassroomWithNullClassroomId() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        try {
            classroomManager.addEquipment(null, equipmentType.getId(), new Quantity(12));
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotAddEquipmentToClassroomIfClassroomDoesNotExists() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        try {
            classroomManager.addEquipment(25664L, equipmentType.getId(), new Quantity(12));
            fail();
        } catch (ClassroomNotFoundException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotAddEquipmentToClassroomIfRoomEquipmentIsNull() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        try {
            classroomManager.addEquipment(model.getId(), null, new Quantity(12));
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotAddEquipmentToClassroomIfEquipmentTypeDoesNotExists() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType.setId(1235L);

        try {
            classroomManager.addEquipment(model.getId(), equipmentType.getId(), new Quantity(12));
            fail();
        } catch (EquipmentTypeNotFoundException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotAddEquipmentIfQuantityIsNull() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        try {
            classroomManager.addEquipment(model.getId(), equipmentType.getId(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }


}