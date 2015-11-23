package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.core.model.classroom.equipement.Quantity;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Anthony on 22/11/2015.
 */
@Entity
@Table(name = "room_equipment_association")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.classroom", joinColumns = @JoinColumn(name = "CLASSROOM_ID")),
        @AssociationOverride(name = "primaryKey.equipmentType", joinColumns = @JoinColumn(name = "EQUIPMENT_TYPE_ID"))
})
public class RoomEquipmentDomain implements ModelTransformable<RoomEquipment, RoomEquipmentDomainId> {

    @EmbeddedId
    private RoomEquipmentDomainId primaryKey = new RoomEquipmentDomainId();

    private int quantity;

    public RoomEquipmentDomain() {
    }

    public RoomEquipmentDomain(RoomEquipment roomEquipment, ClassroomDomain classroomDomain) {
        if (Objects.isNull(roomEquipment)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + RoomEquipment.class.getName());
        }
        if (Objects.isNull(classroomDomain)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + ClassroomDomain.class.getName());
        }

        EquipmentTypeDomain equipmentTypeDomain = new EquipmentTypeDomain(roomEquipment.getEquipmentType());
        this.primaryKey = new RoomEquipmentDomainId(classroomDomain, equipmentTypeDomain);
        this.quantity = roomEquipment.getQuantity();
    }


    @Override
    public RoomEquipmentDomainId getId() {
        return primaryKey;
    }

    @Transient
    public ClassroomDomain getClassroom() {
        return primaryKey.getClassroom();
    }

    @Transient
    public EquipmentTypeDomain getEquipmentType() {
        return primaryKey.getEquipmentType();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public RoomEquipment toModel() {
        return new RoomEquipment(getEquipmentType().toModel(), new Quantity(quantity));
    }

}
