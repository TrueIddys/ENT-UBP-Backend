package org.ubp.ent.backend.model.classroom;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class CapacityTest {

    @Test
    public void shouldNotInstantiateWithZeroCapacity() {
        try {
            new Capacity(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithNegativeCapacity() {
        try {
            new Capacity(-10);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldInstantiateAndDefineAttributes() {
        Capacity capacity = new Capacity(12);
        assertThat(capacity.getMaxCapacity()).isEqualTo(12);
    }

}