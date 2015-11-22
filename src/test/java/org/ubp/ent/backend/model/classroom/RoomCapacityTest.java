package org.ubp.ent.backend.model.classroom;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class RoomCapacityTest {

    public static RoomCapacity createOne() {
        return new RoomCapacity(12);
    }

    @Test
    public void shouldNotInstantiateWithZeroCapacity() {
        try {
            new RoomCapacity(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithNegativeCapacity() {
        try {
            new RoomCapacity(-10);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }
}
