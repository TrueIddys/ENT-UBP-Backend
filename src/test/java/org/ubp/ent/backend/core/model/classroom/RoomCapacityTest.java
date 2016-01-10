package org.ubp.ent.backend.core.model.classroom;


import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void shouldInstantiate() {
        int maxCapacity = 22;
        RoomCapacity roomCapacity = new RoomCapacity(maxCapacity);

        assertThat(roomCapacity.getMaxCapacity().intValue()) .isEqualTo(maxCapacity);
    }
}
