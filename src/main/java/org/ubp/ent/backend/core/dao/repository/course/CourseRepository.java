package org.ubp.ent.backend.core.dao.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.course.CourseDomain;

/**
 * Created by Maxime on 15/02/2016.
 */
public interface CourseRepository extends JpaRepository<CourseDomain, Long> {

}
