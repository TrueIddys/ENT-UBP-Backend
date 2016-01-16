package org.ubp.ent.backend.core.dao.repository.teacher.contact.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressTypeDomain;

/**
 * Created by Anthony on 14/01/2016.
 */
public interface AddressTypeRepository extends JpaRepository<AddressTypeDomain, Long> {

}
