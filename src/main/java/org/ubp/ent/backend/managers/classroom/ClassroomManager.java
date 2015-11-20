package org.ubp.ent.backend.managers.classroom;

import org.ubp.ent.backend.model.classroom.Classroom;

import java.util.List;

/**
 * Created by Anthony on 20/11/2015.
 */
public interface ClassroomManager {

    List<Classroom> findAll();

}
