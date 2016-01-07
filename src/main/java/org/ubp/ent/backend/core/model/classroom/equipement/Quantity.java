package org.ubp.ent.backend.core.model.classroom.equipement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Quantity {

    private final Integer maxQuantity;

    @JsonCreator
    public Quantity(@JsonProperty("maxQuantity") Integer maxQuantity) {
        if (maxQuantity == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a null maxQuantity.");
        }
        if (maxQuantity < 1) {
            throw new ModelConstraintViolationException("Cannot build a " + getClass().getName() + " without a maxQuantity lesser than 1.");
        }
        this.maxQuantity = maxQuantity;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }
}
