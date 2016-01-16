package org.ubp.ent.backend.core.model.teacher;

import org.ubp.ent.backend.core.model.teacher.contact.Contact;
import org.ubp.ent.backend.core.model.teacher.name.Name;

/**
 * Created by Anthony on 16/01/2016.
 */
public class OutsiderTeacher extends Teacher {

    public OutsiderTeacher(Name name, Contact contact) {
        super(name, contact);
    }
}
