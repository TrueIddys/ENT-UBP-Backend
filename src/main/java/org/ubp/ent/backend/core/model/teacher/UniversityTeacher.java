package org.ubp.ent.backend.core.model.teacher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.name.Name;

/**
 * Created by Anthony on 11/01/2016.
 */
public class UniversityTeacher extends Teacher {

    @JsonCreator
    public UniversityTeacher(@JsonProperty("name") Name name, @JsonProperty("contact") Contact contact) {
        super(name, contact);
    }

}
