package org.ubp.ent.backend.model.classroom.equipements;

import java.util.Objects;

/**
 * Created by Anthony on 20/11/2015.
 */
public class Quantity {

    private final Integer quantity;

    public Quantity(Integer quantity) {
        if (Objects.isNull(quantity)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a null quantity.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a quantity lesser than 1.");
        }
        this.quantity = quantity;
    }

}
