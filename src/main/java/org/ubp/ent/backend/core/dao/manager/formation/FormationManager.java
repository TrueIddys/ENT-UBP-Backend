package org.ubp.ent.backend.core.dao.manager.formation;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.formation.FormationRepository;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomain;
import org.ubp.ent.backend.core.domains.formation.FormationLeafDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.FormationResourceNotFoundException;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;

import javax.inject.Inject;

/**
 * Created by Anthony on 26/02/2016.
 */
@Service
public class FormationManager {

    @Inject
    private FormationCache cache;

    @Inject
    private FormationRepository formationRepository;

    public FormationComposite createRoot(FormationComposite root) {
        if (root == null) {
            throw new IllegalArgumentException("Cannot persist a null " + FormationComposite.class.getName());
        }
        if (root.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + FormationComposite.class.getName() + " which already has an ID.");
        }
        if (this.cache.getRoot() != null) {
            throw new IllegalArgumentException("A root formation is already present in database, root id is : " + this.cache.getRoot().getId());
        }
        FormationCompositeDomain domain = new FormationCompositeDomain(root);
        this.formationRepository.saveAndFlush(domain);

        this.cache.evict();
        return this.cache.getRoot();
    }

    public FormationComposite createComposite(Long parentId, FormationComposite model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + FormationComposite.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + FormationComposite.class.getName() + " which already has an ID.");
        }
        FormationComposite parent = this.findCompositeById(parentId);
        FormationCompositeDomain childDomain = new FormationCompositeDomain(model);

        FormationCompositeDomain parentDomain = new FormationCompositeDomain(parent);
        parentDomain.addFormation(childDomain);

        childDomain = this.formationRepository.saveAndFlush(childDomain);
        this.formationRepository.saveAndFlush(parentDomain);

        this.cache.evict();
        return childDomain.toModel();
    }

    public FormationLeaf createLeaf(Long parentId, FormationLeaf model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + FormationComposite.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + FormationLeaf.class.getName() + " which already has an ID.");
        }
        FormationComposite parent = this.findCompositeById(parentId);
        FormationLeafDomain leafDomain = new FormationLeafDomain(model);

        FormationCompositeDomain parentDomain = new FormationCompositeDomain(parent);
        parentDomain.addFormation(leafDomain);


        leafDomain = this.formationRepository.saveAndFlush(leafDomain);
        this.formationRepository.saveAndFlush(parentDomain);

        this.cache.evict();
        return leafDomain.toModel();
    }

    public FormationComposite findRoot() {
        FormationComposite root = this.cache.getRoot();
        if (root == null) {
            throw new FormationResourceNotFoundException("No root found, most likely because there is no " + FormationComposite.class.getName() + " in database");
        }
        return root;
    }

    public FormationComposite findCompositeById(Long id) {
        FormationComposite composite = this.cache.getCompositeById(id);
        if (composite == null) {
            throw new FormationResourceNotFoundException("No " + FormationComposite.class.getName() + " with id : " + id);
        }
        return composite;
    }

    public FormationLeaf findLeafById(Long id) {
        FormationLeaf leaf = this.cache.getLeafById(id);
        if (leaf == null) {
            throw new FormationResourceNotFoundException("No " + FormationLeaf.class.getName() + " with id : " + id);
        }
        return leaf;
    }

}
