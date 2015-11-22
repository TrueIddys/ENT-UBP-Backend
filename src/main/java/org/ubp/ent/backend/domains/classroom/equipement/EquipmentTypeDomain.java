package org.ubp.ent.backend.domains.classroom.equipement;

import org.ubp.ent.backend.domains.ModelTransformable;
import org.ubp.ent.backend.model.classroom.equipement.EquipmentType;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Anthony on 21/11/2015.
 */
@Entity
@Table(name = "equipment_type")
public class EquipmentTypeDomain implements ModelTransformable<EquipmentType, Long> {

    @Id
    @GeneratedValue
    @Column(name = "EQUIPMENT_TYPE_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    public EquipmentTypeDomain(EquipmentType equipmentType) {
        if (Objects.isNull(equipmentType)) {
            throw new IllegalArgumentException("Cannot create a " + getClass().getName() + " with a null " + EquipmentType.class.getName());
        }
        id = equipmentType.getId();
        name = equipmentType.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public EquipmentType toModel() {
        EquipmentType equipmentType = new EquipmentType(name);
        equipmentType.setId(id);
        return equipmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentTypeDomain that = (EquipmentTypeDomain) o;
        return com.google.common.base.Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id);
    }
}
