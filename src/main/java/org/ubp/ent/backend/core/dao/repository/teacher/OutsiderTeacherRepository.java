package org.ubp.ent.backend.core.dao.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teacher.OutsiderTeacherDomain;

/**
 * Created by Anthony on 16/01/2016.
 */
public interface OutsiderTeacherRepository extends JpaRepository<OutsiderTeacherDomain, Long> {
}
