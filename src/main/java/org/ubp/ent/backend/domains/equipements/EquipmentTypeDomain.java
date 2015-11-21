package org.ubp.ent.backend.domains.equipements;

import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by Anthony on 21/11/2015.
 */
@Entity
public class EquipmentTypeDomain {

    @Id
    private String name;

    public EquipmentTypeDomain() {

    }

    public EquipmentTypeDomain(EquipmentType equipmentType) {
        if (Objects.isNull(equipmentType)) {
            throw new IllegalArgumentException("Cannot create a " + getClass().getName() + " with a null " + EquipmentType.class.getName());
        }
        name = equipmentType.getName();
    }

    public String getName() {
        return name;
    }

    public EquipmentType toModel() {
        return new EquipmentType(name);
    }

}
