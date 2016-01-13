package org.ubp.ent.backend.core.model.teacher.contact;

import com.google.common.base.Objects;

/**
 * Created by Anthony on 13/01/2016.
 */
public class ContactDetailsWrapper<T extends ContactDetailsType> {

    private Long id;
    private T type;

    public ContactDetailsWrapper(T type) {
        if (type == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a type");
        }
        this.type = type;
    }

    protected T getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetailsWrapper<?> that = (ContactDetailsWrapper<?>) o;
        return Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }
}
