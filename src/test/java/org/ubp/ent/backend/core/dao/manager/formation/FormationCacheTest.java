package org.ubp.ent.backend.core.dao.manager.formation;

import org.junit.After;
import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.formation.FormationRepository;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomain;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomainTest;
import org.ubp.ent.backend.core.domains.formation.FormationLeafDomain;
import org.ubp.ent.backend.core.domains.formation.FormationLeafDomainTest;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 26/02/2016.
 */
public class FormationCacheTest extends WebApplicationTest {

    @Inject
    private FormationRepository repository;

    @Inject
    private FormationCache cache;

    @After
    public void evictCache() {
        cache.evict();
    }


    @Test
    public void shouldEvictCache() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        root = repository.saveAndFlush(root);

        assertThat(cache.getRoot()).isEqualTo(root.toModel());

        root.addFormation(FormationLeafDomainTest.createOneEmpty());
        root = repository.saveAndFlush(root);
        evictCache();

        assertThat(cache.getRoot().getFormations()).hasSize(1);
    }

    @Test
    public void shouldReturnsNullIfNoCompositeAreInDatabase() {
        assertThat(cache.getRoot()).isNull();
    }

    @Test
    public void shouldReturnsRoot() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        root = repository.saveAndFlush(root);

        assertThat(cache.getRoot()).isEqualTo(root.toModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailGetCompositeByIdWithNull() {
        cache.getCompositeById(null);
    }

    @Test
    public void shouldReturnsNullGetCompositeByIdWithNonExisting() {
        assertThat(cache.getCompositeById(222L)).isNull();
    }

    @Test
    public void shouldGetCompositeById() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        root = repository.saveAndFlush(root);
        cache.evict();

        assertThat(cache.getCompositeById(root.getId())).isEqualTo(root.toModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailGetLeafByIdWithNull() {
        cache.getLeafById(null);
    }

    @Test
    public void shouldReturnsNullGetLeafByIdWithNonExisting() {
        assertThat(cache.getLeafById(252L)).isNull();
    }

    @Test
    public void shouldGetLeafById() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        root = repository.saveAndFlush(root);

        root.addFormation(FormationLeafDomainTest.createOneEmpty());
        root = repository.saveAndFlush(root);
        evictCache();

        FormationLeaf leafModel = ((FormationLeafDomain) root.getFormations().get(0)).toModel();

        assertThat(cache.getLeafById(leafModel.getId())).isEqualTo(leafModel);
    }

}
