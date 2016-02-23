package org.ubp.ent.backend.core.dao.repository.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.wish.WishDomain;
import org.ubp.ent.backend.core.domains.wish.WishDomainId;

/**
 * Created by Anthony on 23/02/2016.
 */
public interface WishRepository extends JpaRepository<WishDomain, WishDomainId> {
}
