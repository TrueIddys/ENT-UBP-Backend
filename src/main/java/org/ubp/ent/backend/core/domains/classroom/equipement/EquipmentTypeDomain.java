package org.ubp.ent.backend.core.domains.classroom.equipement;

import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.classroom.equipement.EquipmentType;

import javax.persistence.*;

/**
 * Created by Anthony on 21/11/2015.
 */
@Entity
@Table(name = "equipment_type")
public class EquipmentTypeDomain implements ModelTransformable<EquipmentType, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EQUIPMENT_TYPE_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @SuppressWarnings("unused")
    protected EquipmentTypeDomain() {
    }

    public EquipmentTypeDomain(EquipmentType equipmentType) {
        if (equipmentType == null) {
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
        return com.google.common.base.Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name);
    }
}
