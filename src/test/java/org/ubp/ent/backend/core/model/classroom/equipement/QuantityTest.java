package org.ubp.ent.backend.core.model.classroom.equipement;

import org.junit.Test;

/**
 * Created by Anthony on 20/11/2015.
 */
public class QuantityTest {

    public static Quantity createOne() {
        return new Quantity(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithANullQuantity() {
        new Quantity(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithAZeroQuantity() {
        new Quantity(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithANegativeQuantity() {
        new Quantity(-2);
    }

}