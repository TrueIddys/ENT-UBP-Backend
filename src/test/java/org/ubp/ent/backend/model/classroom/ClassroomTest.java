package org.ubp.ent.backend.model.classroom;

import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Anthony on 20/11/2015.
 */
public class ClassroomTest {

    private String createValidName() {
        return "Default name";
    }

    private Capacity createValidCapacity() {
        return new Capacity(12);
    }

    @Test
    public void shouldNotInstantiateWithEmptyName() {
        try {
            new Classroom(null, createValidCapacity());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldNotInstantiateWithNullName() {
        try {
            new Classroom(" ", createValidCapacity());
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }


    @Test
    public void shouldNotInstantiateWithNullCapacity() {
        try {
            new Classroom(createValidName(), null);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isNotEmpty();
        }
    }

    @Test
    public void shouldInstantiateAndDefineAttributes() {
        String name = createValidName();
        Capacity capacity = createValidCapacity();

        Classroom classroom = new Classroom(name, capacity);
        assertThat(classroom.getName()).isEqualTo(name);
        assertThat(classroom.getCapacity()).isEqualTo(capacity);
    }
}