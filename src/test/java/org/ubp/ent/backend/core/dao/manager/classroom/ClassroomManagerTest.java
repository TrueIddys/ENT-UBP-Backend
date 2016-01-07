package org.ubp.ent.backend.core.dao.manager.classroom;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.ubp.ent.backend.core.dao.manager.classroom.equipement.EquipmentTypeManager;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedClass;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.ClassroomResourceNotFoundException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EquipmentTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.classroom.Classroom;
import org.ubp.ent.backend.core.model.classroom.ClassroomTest;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentTypeTest;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdWithNull() {
        classroomManager.findOneById(null);
    }

    @Test(expected = ClassroomResourceNotFoundException.class)
    public void shouldFailFindOneByIdWithNonExistingId() {
        classroomManager.findOneById(205L);
    }

    @Test
    public void shouldFindOneByIdJoiningEquipments() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);
        RoomEquipment roomEquipment = classroomManager.addEquipment(model.getId(), equipmentType.getId(), new Quantity(12));

        Classroom fetched = classroomManager.findOneByIdJoiningEquipments(model.getId());
        assertThat(fetched).isEqualTo(model);
        assertThat(fetched.getEquipments()).containsExactly(roomEquipment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindOneByIdJoiningEquipmentsWithNull() {
        classroomManager.findOneByIdJoiningEquipments(null);
    }

    @Test(expected = ClassroomResourceNotFoundException.class)
    public void shouldFailFindOneByIdJoiningEquipmentsWithNonExistingId() {
        classroomManager.findOneByIdJoiningEquipments(205L);
    }


    @Test
    public void shouldFindAllJoiningEquipments() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);
        RoomEquipment roomEquipment = classroomManager.addEquipment(model.getId(), equipmentType.getId(), new Quantity(12));

        List<Classroom> fetched = classroomManager.findAllJoiningEquipments();
        assertThat(fetched.get(0)).isEqualTo(model);
        assertThat(fetched.get(0).getEquipments()).containsExactly(roomEquipment);
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
    public void shouldSetIdOnReferenceWhenCreating() {
        Classroom model = ClassroomTest.createOne("SL5");

        classroomManager.create(model);

        assertThat(model.getId()).isNotNull();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldFailCreateTwoClassroomWithTheSameName() {
        Classroom model = ClassroomTest.createOne();
        classroomManager.create(model);

        Classroom model2 = ClassroomTest.createOne();
        classroomManager.create(model2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateWithNull() {
        classroomManager.create(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedClass.class)
    public void shouldFailCreateIfIdIsDefined() {
        Classroom model = ClassroomTest.createOne();
        model.setId(25L);

        classroomManager.create(model);
    }

    @Test
    public void shouldNotCreateEquipmentInClassroomOnCascade() {
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

        Classroom fetched = classroomManager.findOneByIdJoiningEquipments(model.getId());
        assertThat(fetched.getEquipments()).hasSize(1);
        assertThat(fetched.getEquipments().iterator().next().getEquipmentType()).isEqualTo(equipmentType);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddEquipmentToClassroomWithNullClassroomId() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        classroomManager.addEquipment(null, equipmentType.getId(), new Quantity(12));
    }

    @Test(expected = ClassroomResourceNotFoundException.class)
    public void shouldNotAddEquipmentToClassroomIfClassroomDoesNotExists() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        classroomManager.addEquipment(25664L, equipmentType.getId(), new Quantity(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddEquipmentToClassroomIfRoomEquipmentIsNull() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        classroomManager.addEquipment(model.getId(), null, new Quantity(12));
    }

    @Test(expected = EquipmentTypeResourceNotFoundException.class)
    public void shouldNotAddEquipmentToClassroomIfEquipmentTypeDoesNotExists() {
        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType.setId(1235L);

        classroomManager.addEquipment(model.getId(), equipmentType.getId(), new Quantity(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddEquipmentIfQuantityIsNull() {
        EquipmentType equipmentType = EquipmentTypeTest.createOne("Computer");
        equipmentType = equipmentTypeManager.create(equipmentType);

        Classroom model = ClassroomTest.createOne("SCI_3006");
        model = classroomManager.create(model);

        classroomManager.addEquipment(model.getId(), equipmentType.getId(), null);
    }


}