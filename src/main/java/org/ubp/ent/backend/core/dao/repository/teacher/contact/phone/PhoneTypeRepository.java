package org.ubp.ent.backend.core.dao.repository.teacher.contact.phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.phone.PhoneTypeDomain;

/**
 * Created by Anthony on 15/01/2016.
 */
public interface PhoneTypeRepository extends JpaRepository<PhoneTypeDomain, Long> {

}
