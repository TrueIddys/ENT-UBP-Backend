package org.ubp.ent.backend.core.model.classroom.equipement;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Anthony on 20/11/2015.
 */
public class QuantityTest {

    public static Quantity createOne() {
        return new Quantity(ThreadLocalRandom.current().nextInt(1, 25));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithANullQuantity() {
        new Quantity(null);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldNotInstantiateWithAZeroQuantity() {
        new Quantity(0);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldNotInstantiateWithANegativeQuantity() {
        new Quantity(-2);
    }

}
