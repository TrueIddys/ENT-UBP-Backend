package org.ubp.ent.backend.core.dao.manager.teacher.contact.phone;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.phone.PhoneTypeRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.phone.PhoneTypeDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.PhoneTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.phone.PhoneType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 15/01/2016.
 */
@Service
public class PhoneTypeManager {

    @Inject
    private PhoneTypeRepository repository;

    public PhoneType create(PhoneType model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + PhoneType.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + PhoneType.class.getName() + " which already has an ID.");
        }
        PhoneTypeDomain domain = new PhoneTypeDomain(model);
        domain = repository.saveAndFlush(domain);
        model.setId(domain.getId());

        return domain.toModel();
    }

    public PhoneType findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + PhoneType.class.getName() + " with a null id.");
        }
        PhoneTypeDomain domain = repository.findOne(id);

        if (domain == null) {
            throw new PhoneTypeResourceNotFoundException("No " + PhoneType.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<PhoneType> findAll() {
        List<PhoneTypeDomain> domains = repository.findAll();

        return domains.stream().map(PhoneTypeDomain::toModel).collect(Collectors.toList());
    }

}
