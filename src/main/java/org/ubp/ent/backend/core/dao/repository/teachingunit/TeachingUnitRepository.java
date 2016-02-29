package org.ubp.ent.backend.core.dao.repository.teachingunit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.ubp.ent.backend.core.domains.teachingunit.TeachingUnitDomain;

import java.util.List;

/**
 * Created by Maxime on 28/02/2016.
 */
public interface TeachingUnitRepository extends JpaRepository<TeachingUnitDomain, Long> {

    @Query(value = "SELECT t FROM TeachingUnitDomain t JOIN FETCH t.modules")
    List<TeachingUnitDomain> findJoiningModules();

    @Query(value = "SELECT t FROM TeachingUnitDomain t JOIN FETCH t.modules WHERE t.id = :id")
    TeachingUnitDomain findOneByIdJoiningModules(@Param("id") Long id);
}
