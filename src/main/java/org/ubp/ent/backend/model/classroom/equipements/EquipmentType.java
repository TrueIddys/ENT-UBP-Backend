package org.ubp.ent.backend.model.classroom.equipements;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentType {

    private String name;

    public EquipmentType(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
