package org.ubp.ent.backend.model.classroom.equipement;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentTypeTest {

    public static EquipmentType createValidEquipmentType() {
        return new EquipmentType("Computer");
    }
    public static EquipmentType createValidEquipmentType(String name) {
        return new EquipmentType(name);
    }

    @Test
    public void shouldNotInstantiateWithEmptyName() {
        try {
            new EquipmentType(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithNullName() {
        try {
            new EquipmentType(" ");
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

}
