package org.ubp.ent.backend.core.domains.classroom.equipement;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Anthony on 22/11/2015.
 */
@Embeddable
public class RoomEquipmentDomainId implements Serializable {

    private static final long serialVersionUID = 6588595785824831533L;

    @ManyToOne
    private ClassroomDomain classroom;

    @ManyToOne
    private EquipmentTypeDomain equipmentType;


    protected RoomEquipmentDomainId() {
    }

    public RoomEquipmentDomainId(ClassroomDomain classroom, EquipmentTypeDomain equipmentType) {
        if (classroom == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + ClassroomDomain.class.getName());
        }
        if (equipmentType == null) {
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
