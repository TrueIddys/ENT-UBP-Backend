package org.ubp.ent.backend.core.dao.repository;

import org.junit.Test;
import org.ubp.ent.backend.core.dao.repository.formation.FormationRepository;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomain;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomainTest;
import org.ubp.ent.backend.core.domains.formation.FormationLeafDomainTest;
import org.ubp.ent.backend.utils.WebIntegrationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anthony on 26/02/2016.
 */
public class FormationRepositoryTest extends WebIntegrationTest {

    @Inject
    private FormationRepository repository;


    @Test
    public void shouldFindRootIfThereIsNothingButRootInDatabase() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");

        repository.saveAndFlush(root);

        FormationCompositeDomain fetchedRoot = repository.findRoot();
        assertThat(fetchedRoot).isNotNull();
        assertThat(fetchedRoot.getName()).isEqualTo(root.getName());
    }

    @Test
    public void shouldFindRootInSimpleStructure() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        FormationCompositeDomain depth1_1 = FormationCompositeDomainTest.createOneEmpty("depth1_1");
        FormationCompositeDomain depth1_2 = FormationCompositeDomainTest.createOneEmpty("depth1_2");
        depth1_1.addFormation(FormationCompositeDomainTest.createOneEmpty());
        depth1_2.addFormation(FormationLeafDomainTest.createOneEmpty());
        depth1_2.addFormation(FormationLeafDomainTest.createOneEmpty());
        root.addFormation(depth1_1);
        root.addFormation(depth1_2);

        repository.saveAndFlush(root);

        FormationCompositeDomain fetchedRoot = repository.findRoot();
        assertThat(fetchedRoot).isNotNull();
        assertThat(fetchedRoot.getName()).isEqualTo(root.getName());
    }

    @Test
    public void shouldFindRootWithAFiveDepthTree() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        FormationCompositeDomain depth1_1 = FormationCompositeDomainTest.createOneEmpty("depth1_1");
        FormationCompositeDomain depth1_2 = FormationCompositeDomainTest.createOneEmpty("depth1_2");
        FormationCompositeDomain depth2 = FormationCompositeDomainTest.createOneEmpty("depth1_2");
        FormationCompositeDomain depth3 = FormationCompositeDomainTest.createOneEmpty("depth1_3");
        FormationCompositeDomain depth4 = FormationCompositeDomainTest.createOneEmpty("depth1_4");
        depth4.addFormation(FormationLeafDomainTest.createOneEmpty());
        depth4.addFormation(FormationLeafDomainTest.createOneEmpty());
        depth3.addFormation(depth4);
        depth2.addFormation(depth3);
        depth1_1.addFormation(depth2);
        depth1_2.addFormation(FormationLeafDomainTest.createOneEmpty());
        depth1_2.addFormation(FormationLeafDomainTest.createOneEmpty());
        root.addFormation(depth1_1);
        root.addFormation(depth1_2);

        repository.saveAndFlush(root);

        FormationCompositeDomain fetchedRoot = repository.findRoot();
        assertThat(fetchedRoot).isNotNull();
        assertThat(fetchedRoot.getName()).isEqualTo(root.getName());
    }
    
    @Test
    public void shouldSaveOnCascade() {
        FormationCompositeDomain root = FormationCompositeDomainTest.createOneEmpty("root");
        FormationCompositeDomain depth1 = FormationCompositeDomainTest.createOneEmpty("depth1_1");
        FormationCompositeDomain depth2 = FormationCompositeDomainTest.createOneEmpty("depth1_2");
        depth1.addFormation(FormationCompositeDomainTest.createOneEmpty());
        depth2.addFormation(FormationLeafDomainTest.createOneEmpty());
        depth2.addFormation(FormationLeafDomainTest.createOneEmpty());
        root.addFormation(depth1);
        root.addFormation(depth2);

        repository.saveAndFlush(root);

        assertThat(repository.count()).isEqualTo(6);
    }

}
