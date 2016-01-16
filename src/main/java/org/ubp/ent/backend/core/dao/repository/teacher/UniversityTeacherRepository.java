package org.ubp.ent.backend.core.dao.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teacher.TeacherDomain;

/**
 * Created by Anthony on 14/01/2016.
 */
public interface UniversityTeacherRepository extends JpaRepository<TeacherDomain, Long> {
}
