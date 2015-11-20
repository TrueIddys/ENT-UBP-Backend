package org.ubp.ent.backend.model.classroom.equipements;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentTypeTest {
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

    @Test
    public void shouldInstantiateAndDefineAttributes() {
        String name = "Computer";

        EquipmentType equipmentType= new EquipmentType(name);
        assertThat(equipmentType.getName()).isEqualTo(name);
    }

}