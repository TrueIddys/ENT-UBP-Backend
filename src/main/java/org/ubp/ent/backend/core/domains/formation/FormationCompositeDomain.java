package org.ubp.ent.backend.core.domains.formation;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.exceptions.database.ModelConstraintViolationException;
import org.ubp.ent.backend.core.model.formation.FormationComponent;
import org.ubp.ent.backend.core.model.formation.FormationComposite;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anthony on 25/02/2016.
 */
@Entity
@DiscriminatorValue(value = "composite")
public class FormationCompositeDomain extends FormationComponentDomain implements ModelTransformable<FormationComposite> {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent")
    private List<FormationComponentDomain> formations = new ArrayList<>();

    @Transient
    private Boolean isLeafContainer;

    protected FormationCompositeDomain() {
        super();
    }

    public FormationCompositeDomain(FormationComposite model) {
        super();
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + FormationComposite.class.getName());
        }
        super.name = model.getName();
        super.setId(model.getId());
        this.isLeafContainer = Boolean.TRUE;
        for (FormationComponent formation : model.getFormations()) {
            if (formation.isLeaf()) {
                this.addFormation(new FormationLeafDomain((FormationLeaf) formation));
            } else {
                this.addFormation(new FormationCompositeDomain((FormationComposite) formation));
            }
        }
    }

    public List<FormationComponentDomain> getFormations() {
        return Collections.unmodifiableList(formations);
    }

    @Override
    public Boolean isLeaf() {
        return Boolean.FALSE;
    }

    @Override
    public FormationComposite toModel() {
        FormationComposite model = new FormationComposite(super.getName());
        model.setId(super.getId());
        for (FormationComponentDomain formation : this.formations) {
            if (formation.isLeaf()) {
                model.addFormation(((FormationLeafDomain) formation).toModel());
            } else {
                model.addFormation(((FormationCompositeDomain) formation).toModel());
            }
        }
        return model;
    }

    public void addFormation(FormationComponentDomain formation) {
        if (formation == null) {
            throw new IllegalArgumentException("Cannot add a null " + FormationComponentDomain.class.getName() + " to a " + getClass().getName());
        }
        // TODO: refactor this messy code
        if (formation.isLeaf()) {
            this.addElement((FormationLeafDomain) formation);
        } else {
            this.addElement((FormationCompositeDomain) formation);
        }
    }

    private void addElement(FormationCompositeDomain formation) {
        if (this.formations.isEmpty()) {
            this.isLeafContainer = Boolean.FALSE;
        }
        if (this.isLeafContainer) {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationCompositeDomain.class.getName() + " and " + FormationLeafDomain.class.getName());
        }

        this.formations.add(formation);
    }

    private void addElement(FormationLeafDomain formation) {
        if (this.formations.isEmpty()) {
            this.isLeafContainer = Boolean.TRUE;
        }
        if (!this.isLeafContainer) {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationCompositeDomain.class.getName() + " and " + FormationLeafDomain.class.getName());
        }

        this.formations.add(formation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormationCompositeDomain other = (FormationCompositeDomain) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
