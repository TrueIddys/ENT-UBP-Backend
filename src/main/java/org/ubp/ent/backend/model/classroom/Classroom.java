package org.ubp.ent.backend.model.classroom;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Classroom {

    private String name;
    private Capacity capacity;

    public Classroom(String name, Capacity capacity) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        if (capacity == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a " + Capacity.class.getName());
        }

        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public Capacity getCapacity() {
        return capacity;
    }

}
