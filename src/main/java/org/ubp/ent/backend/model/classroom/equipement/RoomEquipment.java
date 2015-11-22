package org.ubp.ent.backend.model.classroom.equipement;

import java.util.Objects;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomEquipment {

    private final EquipmentType equipmentType;
    private final Quantity quantity;

    public RoomEquipment(EquipmentType equipmentType, Quantity quantity) {
        if (Objects.isNull(equipmentType)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an " + EquipmentType.class.getName());
        }
        if (Objects.isNull(quantity)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + Quantity.class.getName());
        }
        this.equipmentType = equipmentType;
        this.quantity = quantity;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEquipment equipment = (RoomEquipment) o;
        return com.google.common.base.Objects.equal(equipmentType, equipment.equipmentType);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(equipmentType);
    }

    
}
