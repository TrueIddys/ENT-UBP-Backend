package org.ubp.ent.backend.core.model.classroom.equipement;

import org.junit.Test;

/**
 * Created by Anthony on 20/11/2015.
 */
public class EquipmentTypeTest {

    public static EquipmentType createOne() {
        return createOne("Computer");
    }

    public static EquipmentType createOne(String name) {
        return new EquipmentType(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new EquipmentType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new EquipmentType(" ");
    }

}
