package org.ubp.ent.backend.core.dao.repository.teacher.contact.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressDomain;

/**
 * Created by Anthony on 15/01/2016.
 */
public interface AddressRepository extends JpaRepository<AddressDomain, Long> {
}
