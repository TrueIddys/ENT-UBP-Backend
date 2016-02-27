package org.ubp.ent.backend.core.dao.manager.formation;

import org.junit.Test;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.FormationResourceNotFoundException;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationCompositeTest;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;
import org.ubp.ent.backend.core.model.formation.FormationLeafTest;
import org.ubp.ent.backend.utils.WebApplicationTest;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 27/02/2016.
 */
public class FormationManagerTest extends WebApplicationTest {

    @Inject
    private FormationManager formationManager;

    // inject the case present un manager to reset after each tests
    @Inject
    private FormationCache cache;


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateRootWithNull() {
        formationManager.createRoot(null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateRootIfIdIsAlreadyDefined() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root.setId(123L);
        formationManager.createRoot(root);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateRootIfRootAlreadyExists() {
        formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        formationManager.createRoot(FormationCompositeTest.createOneEmpty());
    }

    @Test
    public void shouldCreateRoot() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = formationManager.createRoot(root);

        assertThat(formationManager.findRoot()).isEqualTo(root);
    }

    @Test
    public void shouldSetIdByReferenceWhenCreatingRoot() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = formationManager.createRoot(root);
        assertThat(root.getId()).isNotNull();
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateCompositeWithNullParentId() {
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        formationManager.createComposite(null, child);
    }

    @Test(expected = FormationResourceNotFoundException.class)
    public void shouldFailCreateCompositeWithNonExistingParent() {
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        formationManager.createComposite(123L, child);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateCompositeWithNullComposite() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        formationManager.createComposite(root.getId(), null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateCompositeWithCompositeIdAlreadyDefined() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        child.setId(123L);
        formationManager.createComposite(root.getId(), child);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailCreateCompositeIfParentContainsLeaf() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        formationManager.createLeaf(root.getId(), FormationLeafTest.createOneEmpty());

        FormationComposite child = FormationCompositeTest.createOneEmpty();
        formationManager.createComposite(root.getId(), child);
    }

    @Test
    public void shouldCreateComposite() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        child = formationManager.createComposite(root.getId(), child);

        assertThat(formationManager.findRoot().getFormations().get(0)).isEqualTo(child);
    }

    @Test
    public void shouldDefineIdByReferenceWhenCreatingComposite() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationComposite child = FormationCompositeTest.createOneEmpty();
        child = formationManager.createComposite(root.getId(), child);
        assertThat(child.getId()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateLeafWithNullParentId() {
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        formationManager.createLeaf(null, child);
    }

    @Test(expected = FormationResourceNotFoundException.class)
    public void shouldFailCreateLeafWithNonExistingParent() {
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        formationManager.createLeaf(123L, child);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailCreateLeafWithNullLeaf() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        formationManager.createLeaf(root.getId(), null);
    }

    @Test(expected = AlreadyDefinedInOnNonPersistedEntity.class)
    public void shouldFailCreateLeafWithLeafIdAlreadyDefined() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationLeaf child = FormationLeafTest.createOneEmpty();
        child.setId(123L);
        formationManager.createLeaf(root.getId(), child);
    }

    @Test(expected = ModelConstraintViolationException.class)
    public void shouldFailCreateLeafIfParentContainsComposite() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        formationManager.createComposite(root.getId(), FormationCompositeTest.createOneEmpty());

        FormationLeaf leaf = FormationLeafTest.createOneEmpty();
        formationManager.createLeaf(root.getId(), leaf);
    }

    @Test
    public void shouldCreateLeaf() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationLeaf leaf = FormationLeafTest.createOneEmpty();
        leaf = formationManager.createLeaf(root.getId(), leaf);

        assertThat(formationManager.findRoot().getFormations().get(0)).isEqualTo(leaf);
    }

    @Test
    public void shouldDefineIdByReferenceWhenCreatingLeaf() {
        FormationComposite root = formationManager.createRoot(FormationCompositeTest.createOneEmpty());
        FormationLeaf leaf = FormationLeafTest.createOneEmpty();
        leaf = formationManager.createLeaf(root.getId(), leaf);
        assertThat(leaf.getId()).isNotNull();
    }

    @Test(expected = FormationResourceNotFoundException.class)
    public void shouldFailFindRootIfThereIsNoRootInDatabase() {
        formationManager.findRoot();
    }

    @Test
    public void shouldFindRoot() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = formationManager.createRoot(root);

        assertThat(formationManager.findRoot()).isEqualTo(root);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindCompositeWithNullId() {
        formationManager.findCompositeById(null);
    }

    @Test(expected = FormationResourceNotFoundException.class)
    public void shouldFailFindCompositeWithNonExisting() {
        formationManager.findCompositeById(123L);
    }

    @Test
    public void shouldFindComposite() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = formationManager.createRoot(root);

        FormationComposite child = FormationCompositeTest.createOneEmpty();
        child = formationManager.createComposite(root.getId(), child);

        assertThat(formationManager.findCompositeById(child.getId())).isEqualTo(child);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailFindLeafWithNullId() {
        formationManager.findLeafById(null);
    }

    @Test(expected = FormationResourceNotFoundException.class)
    public void shouldFailFindLeafWithNonExisting() {
        formationManager.findLeafById(123L);
    }

    @Test
    public void shouldFindLeaf() {
        FormationComposite root = FormationCompositeTest.createOneEmpty();
        root = formationManager.createRoot(root);

        FormationLeaf leaf = FormationLeafTest.createOneEmpty();
        leaf = formationManager.createLeaf(root.getId(), leaf);

        assertThat(formationManager.findLeafById(leaf.getId())).isEqualTo(leaf);
    }

}
