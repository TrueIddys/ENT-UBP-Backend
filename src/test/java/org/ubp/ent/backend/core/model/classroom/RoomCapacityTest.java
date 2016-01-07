package org.ubp.ent.backend.core.model.classroom;


import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomCapacityTest {

    public static RoomCapacity createOne() {
        return new RoomCapacity(12);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldNotInstantiateWithZeroCapacity() {
        new RoomCapacity(0);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldNotInstantiateWithNegativeCapacity() {
        new RoomCapacity(-10);
    }
}
