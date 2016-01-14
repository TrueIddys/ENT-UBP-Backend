package org.ubp.ent.backend.core.domains.teacher.contact;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.model.teacher.contact.ContactDetailsType;

import javax.persistence.*;

/**
 * Created by Anthony on 13/01/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ContactDetailsTypeDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "TYPE_ID", unique = true)
    private String name;

    protected ContactDetailsTypeDomain() {
    }

    protected ContactDetailsTypeDomain(ContactDetailsType model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + ContactDetailsType.class.getName());
        }
        this.id = model.getId();
        this.name = model.getName();
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
        ContactDetailsTypeDomain that = (ContactDetailsTypeDomain) o;
        return Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
