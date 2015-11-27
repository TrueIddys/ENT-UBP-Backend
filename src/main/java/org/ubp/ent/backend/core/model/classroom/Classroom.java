package org.ubp.ent.backend.core.model.classroom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.ubp.ent.backend.core.model.classroom.equipement.RoomEquipment;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Classroom {

    private Long id;
    private final String name;
    private final RoomCapacity roomCapacity;
    private final Set<RoomEquipment> equipments = new HashSet<>();

    @JsonCreator
    public Classroom(@JsonProperty("name")final  String name, @JsonProperty("roomCapacity") final RoomCapacity roomCapacity) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        if (Objects.isNull(roomCapacity)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + RoomCapacity.class.getName());
        }
        this.name = name;
        this.roomCapacity = roomCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public RoomCapacity getRoomCapacity() {
        return roomCapacity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return com.google.common.base.Objects.equal(name, classroom.name);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name);
    }
}
