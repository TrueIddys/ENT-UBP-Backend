package org.ubp.ent.backend.core.model.classroom;


import org.junit.Test;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomCapacityTest {

    public static RoomCapacity createOne() {
        return new RoomCapacity(12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithZeroCapacity() {
        new RoomCapacity(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNegativeCapacity() {
        new RoomCapacity(-10);
    }
}
