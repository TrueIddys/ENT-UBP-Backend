package org.ubp.ent.backend.repository.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.domains.classroom.ClassroomDomain;
import org.ubp.ent.backend.model.classroom.Classroom;

/**
 * Created by Anthony on 23/11/2015.
 */
public interface ClassroomRepository extends JpaRepository<ClassroomDomain, Long> {
}
