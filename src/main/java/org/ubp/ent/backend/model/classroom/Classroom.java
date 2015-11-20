package org.ubp.ent.backend.model.classroom;

import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.model.classroom.equipements.RoomEquipment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Classroom {

    private final String name;
    private final RoomCapacity roomCapacity;
    private final Set<RoomEquipment> equipments;

    public Classroom(String name, RoomCapacity roomCapacity) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        if (Objects.isNull(roomCapacity)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + RoomCapacity.class.getName());
        }

        this.name = name;
        this.roomCapacity = roomCapacity;
        equipments = new HashSet<>();
    }

    public Set<RoomEquipment> getEquipments() {
        return Collections.unmodifiableSet(equipments);
    }

    public void addEquipment(RoomEquipment equipment) {
        if (Objects.isNull(equipment)) {
            throw new IllegalArgumentException("Cannot add a null " + RoomEquipment.class.getName() + " to a " + getClass().getName());
        }
        this.equipments.add(equipment);
    }

}
