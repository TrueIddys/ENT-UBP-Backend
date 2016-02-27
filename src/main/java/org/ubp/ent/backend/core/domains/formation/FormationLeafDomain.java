package org.ubp.ent.backend.core.domains.formation;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.formation.FormationLeaf;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Anthony on 25/02/2016.
 */
@Entity
@DiscriminatorValue(value = "leaf")
public class FormationLeafDomain extends FormationComponentDomain implements ModelTransformable<FormationLeaf> {

    protected FormationLeafDomain() {
    }

    public FormationLeafDomain(FormationLeaf model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + FormationLeaf.class.getName());
        }
        this.name = model.getName();
        super.setId(model.getId());
    }

    public Boolean isLeaf() {
        return Boolean.TRUE;
    }

    @Override
    public FormationLeaf toModel() {
        FormationLeaf model = new FormationLeaf(super.getName());
        model.setId(super.getId());
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormationLeafDomain other = (FormationLeafDomain) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
