package org.ubp.ent.backend.core.dao.repository.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.classroom.ClassroomDomain;

/**
 * Created by Anthony on 23/11/2015.
 */
public interface ClassroomRepository extends JpaRepository<ClassroomDomain, Long> {
}
