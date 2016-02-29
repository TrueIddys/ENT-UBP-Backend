package org.ubp.ent.backend.core.domains.teachingunit;

import com.google.common.base.Objects;
import org.ubp.ent.backend.core.domains.ModelTransformable;
import org.ubp.ent.backend.core.model.teachingunit.TeachingUnit;

import javax.persistence.*;

/**
 * Created by Maxime on 28/02/2016.
 */

@Entity
@Table(name = "teachingunit")
public class TeachingUnitDomain implements ModelTransformable<TeachingUnit>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEACHINGUNIT_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @SuppressWarnings("unused")
    protected TeachingUnitDomain() {
    }

    public TeachingUnitDomain(TeachingUnit model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " with a null " + TeachingUnit.class.getName());
        }
        id = model.getId();
        name = model.getName();
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
    public TeachingUnit toModel() {
        TeachingUnit teachingUnit = new TeachingUnit(name);
        teachingUnit.setId(id);

        return teachingUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingUnitDomain other = (TeachingUnitDomain) o;
        if (this.id == null || other.id == null) return false;
        return Objects.equal(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
