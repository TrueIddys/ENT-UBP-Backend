package org.ubp.ent.backend.core.model.formation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Anthony on 25/02/2016.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = FormationComposite.class, name = "composite"),
                @JsonSubTypes.Type(value = FormationLeaf.class, name = "leaf")
        }
)
public interface FormationComponent {
    Boolean isLeaf();
}
