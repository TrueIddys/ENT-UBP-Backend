package org.ubp.ent.backend.core.dao.manager.formation;

import org.springframework.stereotype.Component;
import org.ubp.ent.backend.core.dao.repository.formation.FormationRepository;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomain;
import org.ubp.ent.backend.core.model.formation.FormationComponent;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anthony on 26/02/2016.
 */
@Component
public class FormationCache {

    private final Map<Long, FormationComponent> components;
    @Inject
    private FormationRepository repository;
    private FormationComponentVisitor visitor;
    private Long rootId;

    private Boolean needRefresh;

    FormationCache() {
        this.components = new ConcurrentHashMap<>();
        this.visitor = new FormationComponentVisitor() {
            @Override
            void onCompositeFound(FormationComposite composite) {
                components.put(composite.getId(), composite);
            }

            @Override
            void onLeafFound(FormationLeaf leaf) {
                components.put(leaf.getId(), leaf);
            }
        };
        this.needRefresh = Boolean.TRUE;
    }

    private void refresh() {
        this.evict();
        this.needRefresh = Boolean.FALSE;

        FormationCompositeDomain rootDomain = repository.findRoot();
        if (rootDomain == null) {
            return;
        }
        FormationComposite rootModel = rootDomain.toModel();
        this.rootId = rootModel.getId();
        this.components.put(rootModel.getId(), rootModel);
        this.visitor.crawlFromRoot(rootModel);
    }

    private void refreshIfNeeded() {
        if (needRefresh) {
            refresh();
        }
    }

    public void evict() {
        this.needRefresh = Boolean.TRUE;
        this.rootId = null;
        this.components.clear();
    }

    public FormationComposite getRoot() {
        this.refreshIfNeeded();

        if (rootId == null) {
            return null;
        }
        FormationComponent component = this.components.get(this.rootId);
        if (component == null) {
            return null;
        }
        return (FormationComposite) component;
    }

    public FormationComposite getCompositeById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + FormationComposite.class + " if given id is null");
        }
        this.refreshIfNeeded();

        FormationComponent component = this.components.get(id);
        if (component == null || component.isLeaf()) {
            return null;
        }
        return (FormationComposite) component;
    }

    public FormationLeaf getLeafById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + FormationLeaf.class + " if given id is null");
        }
        this.refreshIfNeeded();

        FormationComponent component = this.components.get(id);
        if (component == null || !component.isLeaf()) {
            return null;
        }
        return (FormationLeaf) component;
    }


    private abstract static class FormationComponentVisitor {

        abstract void onCompositeFound(FormationComposite composite);

        abstract void onLeafFound(FormationLeaf leaf);

        final void crawlFromRoot(FormationComposite root) {
            for (FormationComponent component : root.getFormations()) {
                if (component.isLeaf()) {
                    this.onLeafFound((FormationLeaf) component);
                } else {
                    FormationComposite composite = (FormationComposite) component;
                    this.onCompositeFound(composite);
                    this.crawlFromRoot(composite);
                }
            }
        }

    }
}
