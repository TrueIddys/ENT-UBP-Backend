package org.ubp.ent.backend.manager.classroom.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.manager.classroom.ClassroomManager;
import org.ubp.ent.backend.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.model.classroom.Classroom;
import org.ubp.ent.backend.model.classroom.ClassroomTest;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;


/**
 * Created by Anthony on 23/11/2015.
 */
public class ClassroomManagerJPATest extends WebApplicationTest {

    @Inject
    private ClassroomManager classroomManager;

    @Inject
    private EquipmentTypeManager equipmentTypeManager;

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
    public void shouldNotCreateEquipmentByClassroomOnCascade() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        Classroom model = ClassroomTest.createOne("SCI_3006");
        model.addEquipment(new RoomEquipment(equipmentType, new Quantity(12)));
        classroomManager.create(model);

        Classroom modelDB = classroomManager.findAll().get(0);

        assertThat(modelDB.getEquipments()).hasSize(0);
    }

}