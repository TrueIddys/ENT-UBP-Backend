package org.ubp.ent.backend.core.model.formation;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationCompositeTest {

    private static String createValidName() {
        int length = ThreadLocalRandom.current().nextInt(6, 12);
        String name = RandomStringUtils.randomAlphanumeric(length);
        return StringUtils.deleteWhitespace(name);
    }

    public static FormationComposite createOneEmpty() {
        return createOneEmpty(createValidName());
    }

    public static FormationComposite createOneEmpty(String name) {
        return new FormationComposite(name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullName() {
        new FormationComposite(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithEmptyName() {
        new FormationComposite(" ");
    }

    @Test
    public void shouldInstantiate() {
        FormationComposite model = new FormationComposite("Master");

        assertThat(model.getId()).isNull();
        assertThat(model.getName()).isEqualTo("Master");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddFormationWithNull() {
        FormationComposite model = createOneEmpty();
        model.addFormation(null);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationLeafIfAlreadyContainsAFormationComposite() {
        FormationComposite model = createOneEmpty();
        model.addFormation(createOneEmpty());
        model.addFormation(FormationLeafTest.createOneEmpty());
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationCompositeIfAlreadyContainsAFormationLeaf() {
        FormationComposite model = createOneEmpty();
        model.addFormation(FormationLeafTest.createOneEmpty());
        model.addFormation(createOneEmpty());
    }

    @Test
    public void shouldAddFormation() {
        FormationComposite model = createOneEmpty();
        FormationLeaf leaf1 = FormationLeafTest.createOneEmpty();
        FormationLeaf leaf2 = FormationLeafTest.createOneEmpty();

        model.addFormation(leaf1);
        model.addFormation(leaf2);

        assertThat(model.getFormations()).hasSize(2);
    }

    @Test
    public void shouldReturnFalseIsLeaf() {
        assertThat(createOneEmpty().isLeaf()).isFalse();
    }

    @Test
    public void shouldBeEqualById() {
        FormationComposite model1 = createOneEmpty();
        FormationComposite model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(12L);

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        FormationComposite model1 = createOneEmpty();
        FormationComposite model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(13L);

        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        FormationComposite model1 = createOneEmpty();
        FormationComposite model2 = createOneEmpty();

        assertThat(model1).isNotEqualTo(model2);
    }
}
