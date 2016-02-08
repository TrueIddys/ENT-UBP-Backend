package org.ubp.ent.backend.core.model.teacher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.name.Name;

/**
 * Created by Anthony on 16/01/2016.
 */
public class OutsiderTeacher extends Teacher {

    @JsonCreator
    public OutsiderTeacher(@JsonProperty("name") Name name, @JsonProperty("contact") Contact contact) {
        super(name, contact);
    }

}
