package org.ubp.ent.backend.core.domains.formation;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationCompositeTest;
import org.ubp.ent.backend.core.model.formation.FormationLeafTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationCompositeDomainTest {

    public static FormationCompositeDomain createOneEmpty() {
        return new FormationCompositeDomain(FormationCompositeTest.createOneEmpty());
    }

    public static FormationCompositeDomain createOneEmpty(String name) {
        return new FormationCompositeDomain(FormationCompositeTest.createOneEmpty(name));
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new FormationCompositeDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        FormationComposite model = FormationCompositeTest.createOneEmpty();
        model.setId(12L);

        FormationCompositeDomain domain = new FormationCompositeDomain(model);
        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
        assertThat(domain.getFormations()).isEmpty();
    }

    @Test
    public void shouldPopulateFormationsRecursivelyWhenInstantiateFromModel() {
        FormationComposite model = FormationCompositeTest.createOneEmpty();
        FormationComposite subModel = FormationCompositeTest.createOneEmpty();
        subModel.addFormation(FormationLeafTest.createOneEmpty());
        model.addFormation(subModel);

        FormationCompositeDomain domain = new FormationCompositeDomain(model);
        assertThat(domain.getFormations()).hasSize(1);
        assertThat(((FormationCompositeDomain) domain.getFormations().get(0)).getFormations()).hasSize(1);
    }

    @Test
    public void shouldTransformToModel() {
        FormationCompositeDomain domain = createOneEmpty();
        domain.setId(13L);

        FormationComposite model = domain.toModel();
        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName());
        assertThat(model.getFormations()).isEmpty();
    }

    @Test
    public void shouldPopulateFormationsWhenTransformingToModel() {
        FormationCompositeDomain domain = createOneEmpty();
        FormationCompositeDomain subDomain = createOneEmpty();
        subDomain.addFormation(FormationLeafDomainTest.createOneEmpty());
        domain.addFormation(subDomain);

        FormationComposite model = domain.toModel();
        assertThat(model.getFormations()).hasSize(1);
        assertThat(((FormationComposite) model.getFormations().get(0)).getFormations()).hasSize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailAddFormationWithNull() {
        FormationCompositeDomain model = createOneEmpty();
        model.addFormation(null);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationLeafIfAlreadyContainsAFormationComposite() {
        FormationCompositeDomain model = createOneEmpty();
        model.addFormation(createOneEmpty());
        model.addFormation(FormationLeafDomainTest.createOneEmpty());
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailAddFormationWithFormationCompositeIfAlreadyContainsAFormationLeaf() {
        FormationCompositeDomain model = createOneEmpty();
        model.addFormation(FormationLeafDomainTest.createOneEmpty());
        model.addFormation(createOneEmpty());
    }

    @Test
    public void shouldAddFormation() {
        FormationCompositeDomain model = createOneEmpty();
        FormationLeafDomain leaf1 = FormationLeafDomainTest.createOneEmpty();
        FormationLeafDomain leaf2 = FormationLeafDomainTest.createOneEmpty();

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
        FormationCompositeDomain model1 = createOneEmpty();
        FormationCompositeDomain model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(12L);

        assertThat(model1).isEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        FormationCompositeDomain model1 = createOneEmpty();
        FormationCompositeDomain model2 = createOneEmpty();
        model1.setId(12L);
        model2.setId(13L);

        assertThat(model1).isNotEqualTo(model2);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        FormationCompositeDomain model1 = createOneEmpty();
        FormationCompositeDomain model2 = createOneEmpty();

        assertThat(model1).isNotEqualTo(model2);
    }

}
