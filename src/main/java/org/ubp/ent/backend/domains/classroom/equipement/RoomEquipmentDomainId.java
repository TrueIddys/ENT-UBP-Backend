package org.ubp.ent.backend.domains.classroom.equipement;

import com.google.common.base.Objects;
import org.ubp.ent.backend.domains.classroom.ClassroomDomain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Anthony on 22/11/2015.
 */
@Embeddable
public class RoomEquipmentDomainId implements Serializable {

    private static final long serialVersionUID = 6588595785824831533L;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClassroomDomain classroom;

    @ManyToOne(cascade = CascadeType.ALL)
    private EquipmentTypeDomain equipmentType;

    
    public RoomEquipmentDomainId() {
    }

    public RoomEquipmentDomainId(ClassroomDomain classroom, EquipmentTypeDomain equipmentType) {
        if (java.util.Objects.isNull(classroom)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + ClassroomDomain.class.getName());
        }
        if (java.util.Objects.isNull(equipmentType)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + EquipmentTypeDomain.class.getName());
        }
        this.classroom = classroom;
        this.equipmentType = equipmentType;
    }

    public ClassroomDomain getClassroom() {
        return classroom;
    }

    public EquipmentTypeDomain getEquipmentType() {
        return equipmentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEquipmentDomainId that = (RoomEquipmentDomainId) o;
        return Objects.equal(classroom, that.classroom) &&
                Objects.equal(equipmentType, that.equipmentType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(classroom, equipmentType);
    }
}
