package org.ubp.ent.backend.core.dao.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ubp.ent.backend.core.domains.teacher.OutsiderTeacherDomain;

/**
 * Created by Anthony on 10/02/2016.
 */
public interface OutsiderTeacherRepository extends JpaRepository<OutsiderTeacherDomain, Long> {

}
