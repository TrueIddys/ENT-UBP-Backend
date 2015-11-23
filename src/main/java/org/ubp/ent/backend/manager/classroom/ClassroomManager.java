package org.ubp.ent.backend.manager.classroom;

import org.ubp.ent.backend.model.classroom.Classroom;

import java.util.List;

/**
 * Created by Anthony on 20/11/2015.
 */
public interface ClassroomManager {

    Classroom create(Classroom classroom);

    List<Classroom> findAll();


}
