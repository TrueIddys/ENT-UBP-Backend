package org.ubp.ent.backend.core.model.formation;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationLeafTest {

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(6, 12);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }

    public static FormationLeaf createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    public static FormationLeaf createOneEmpty(String name) {
        return new FormationLeaf(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new FormationLeaf(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new FormationLeaf(" ");
    }

    @Test
    public void shouldInstantiate() {
        FormationLeaf model = new FormationLeaf("Master 1 informatique");

        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Master 1 informatique");
    }

    @Test
    public void shouldReturnTrueIsLeaf() {
        assertThat(createOneEmpty().isLeaf()).isTrue();
    }

    @Test
    public void shouldBeEqualById() {
        FormationLeaf model1 = createOneEmpty();
        FormationLeaf model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(12L);

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        FormationLeaf model1 = createOneEmpty();
        FormationLeaf model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(13L);

        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        FormationLeaf model1 = createOneEmpty();
        FormationLeaf model2 = createOneEmpty();

        assertThat(model1).isNotEqualTo(model2);
    }

}
