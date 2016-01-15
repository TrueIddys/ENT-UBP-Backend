package org.ubp.ent.backend.core.dao.manager.teacher.contact.address;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.address.AddressTypeRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.address.AddressTypeDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.AddressTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.address.AddressType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 14/01/2016.
 */
@Service
public class AddressTypeManager {

    @Inject
    private AddressTypeRepository repository;

    public AddressType create(AddressType model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + AddressType.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + AddressType.class.getName() + " which already has an ID.");
        }
        AddressTypeDomain domain = new AddressTypeDomain(model);
        domain = repository.saveAndFlush(domain);
        model.setId(domain.getId());

        return domain.toModel();
    }

    public AddressType findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + AddressType.class.getName() + " with a null id.");
        }
        AddressTypeDomain domain = repository.findOne(id);

        if (domain == null) {
            throw new AddressTypeResourceNotFoundException("No " + AddressType.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<AddressType> findAll() {
        List<AddressTypeDomain> domains = repository.findAll();

        return domains.stream().map(AddressTypeDomain::toModel).collect(Collectors.toList());
    }

}
