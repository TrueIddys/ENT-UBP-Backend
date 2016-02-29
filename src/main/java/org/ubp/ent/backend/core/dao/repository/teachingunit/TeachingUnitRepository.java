package org.ubp.ent.backend.core.dao.repository.teachingunit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teachingunit.TeachingUnitDomain;

/**
 * Created by Maxime on 28/02/2016.
 */
public interface TeachingUnitRepository extends JpaRepository<TeachingUnitDomain, Long> {
}
