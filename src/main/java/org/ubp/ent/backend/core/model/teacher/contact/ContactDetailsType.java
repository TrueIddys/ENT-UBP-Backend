package org.ubp.ent.backend.core.model.teacher.contact;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Anthony on 13/01/2016.
 */
public class ContactDetailsType {

    private Long id;
    private String name;

    public ContactDetailsType(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a type name");
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetailsType that = (ContactDetailsType) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

}
