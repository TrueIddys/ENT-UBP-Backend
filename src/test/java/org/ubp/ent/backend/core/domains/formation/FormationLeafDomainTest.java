package org.ubp.ent.backend.core.domains.formation;

import org.junit.Test;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;
import org.ubp.ent.backend.core.model.formation.FormationLeafTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 25/02/2016.
 */
public class FormationLeafDomainTest {

    public static FormationLeafDomain createOneEmpty() {
        return new FormationLeafDomain(FormationLeafTest.createOneEmpty());
    }

    public static FormationLeafDomain createOneEmpty(String name) {
        return new FormationLeafDomain(FormationLeafTest.createOneEmpty(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInstantiateWithNullModel() {
        new FormationLeafDomain(null);
    }

    @Test
    public void shouldCreateFromModel() {
        FormationLeaf model = FormationLeafTest.createOneEmpty();
        model.setId(12L);

        FormationLeafDomain domain = new FormationLeafDomain(model);
        assertThat(domain.getId()).isEqualTo(model.getId());
        assertThat(domain.getName()).isEqualTo(model.getName());
    }

    @Test
    public void shouldTransformToModel() {
        FormationLeafDomain domain = createOneEmpty();
        domain.setId(13L);

        FormationLeaf model = domain.toModel();
        assertThat(model.getId()).isEqualTo(domain.getId());
        assertThat(model.getName()).isEqualTo(domain.getName());
    }

    @Test
    public void shouldBeEqualById() {
        FormationLeafDomain domain1 = createOneEmpty();
        FormationLeafDomain domain2 = createOneEmpty();
        domain1.setId(14L);
        domain2.setId(14L);

        assertThat(domain1).isEqualTo(domain2);
    }

    @Test
    public void shouldNotBeEqualWithDifferentIds() {
        FormationLeafDomain domain1 = createOneEmpty();
        FormationLeafDomain domain2 = createOneEmpty();
        domain1.setId(12L);
        domain2.setId(13L);

        assertThat(domain1).isNotEqualTo(domain2);
    }

    @Test
    public void shouldNotBeEqualWithNullIds() {
        FormationLeafDomain domain1 = createOneEmpty();
        FormationLeafDomain domain2 = createOneEmpty();

        assertThat(domain1).isNotEqualTo(domain2);
    }

}
