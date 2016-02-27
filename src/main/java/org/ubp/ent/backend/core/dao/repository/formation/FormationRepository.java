package org.ubp.ent.backend.core.dao.repository.formation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ubp.ent.backend.core.domains.formation.FormationComponentDomain;
import org.ubp.ent.backend.core.domains.formation.FormationCompositeDomain;

/**
 * Created by Anthony on 26/02/2016.
 */
public interface FormationRepository extends JpaRepository<FormationComponentDomain, Long> {

    // The comma appear as an error, but it is actually needed.
    @Query(value = "SELECT fc FROM FormationComponentDomain fc WHERE fc.id NOT IN (SELECT DISTINCT child.id FROM FormationComponentDomain fc, IN(fc.formations) child)")
    FormationCompositeDomain findRoot();

}
