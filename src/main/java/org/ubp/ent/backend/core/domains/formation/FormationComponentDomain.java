package org.ubp.ent.backend.core.domains.formation;

import javax.persistence.*;

/**
 * Created by Anthony on 25/02/2016.
 */
@Entity
@Table(name = "formation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "component_type")
public abstract class FormationComponentDomain {

    protected String name;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    protected abstract Boolean isLeaf();
}
