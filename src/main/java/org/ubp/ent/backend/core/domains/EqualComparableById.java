package org.ubp.ent.backend.core.domains;

import com.google.common.base.Objects;

/**
 * Created by Anthony on 13/01/2016.
 */
public abstract class EqualComparableById<T extends EqualComparableById, ID> {

    public abstract ID getId();

    protected boolean checkAdditionalEqualsConstraint(T other) {
        return true;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T that = (T) o;
        if (getId() == null || that.getId() == null) return false;
        if (!this.checkAdditionalEqualsConstraint(that)) return false;
        return Objects.equal(getId(), that.getId());
    }


}
