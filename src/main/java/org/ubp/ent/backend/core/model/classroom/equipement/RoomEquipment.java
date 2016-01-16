package org.ubp.ent.backend.core.model.classroom.equipement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomEquipment {

    private final EquipmentType equipmentType;
    private final Quantity quantity;

    @JsonCreator
    public RoomEquipment(@JsonProperty("equipmentType") EquipmentType equipmentType, @JsonProperty("quantity") Quantity quantity) {
        if (equipmentType == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without an " + EquipmentType.class.getName());
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + Quantity.class.getName());
        }
        this.equipmentType = equipmentType;
        this.quantity = quantity;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public Quantity getQuantity() {
        return quantity;
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
