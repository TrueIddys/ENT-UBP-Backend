package org.ubp.ent.backend.model.classroom.equipements;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentType {

    private final String name;

    public EquipmentType(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name.");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentType that = (EquipmentType) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
