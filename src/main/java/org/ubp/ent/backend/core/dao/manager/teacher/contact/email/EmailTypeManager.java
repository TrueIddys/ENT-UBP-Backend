package org.ubp.ent.backend.core.dao.manager.teacher.contact.email;

import org.springframework.stereotype.Service;
import org.ubp.ent.backend.core.dao.repository.teacher.contact.email.EmailTypeRepository;
import org.ubp.ent.backend.core.domains.teacher.contact.email.EmailTypeDomain;
import org.ubp.ent.backend.core.exceptions.database.AlreadyDefinedInOnNonPersistedEntity;
import org.ubp.ent.backend.core.exceptions.database.notfound.impl.EmailTypeResourceNotFoundException;
import org.ubp.ent.backend.core.model.teacher.contact.email.EmailType;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Anthony on 15/01/2016.
 */
@Service
public class EmailTypeManager {

    @Inject
    private EmailTypeRepository repository;

    public EmailType create(EmailType model) {
        if (model == null) {
            throw new IllegalArgumentException("Cannot persist a null " + EmailType.class.getName());
        }
        if (model.getId() != null) {
            throw new AlreadyDefinedInOnNonPersistedEntity("Cannot persist a " + EmailType.class.getName() + " which already has an ID.");
        }
        EmailTypeDomain domain = new EmailTypeDomain(model);
        domain = repository.saveAndFlush(domain);
        model.setId(domain.getId());

        return domain.toModel();
    }

    public EmailType findOneById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot find a " + EmailType.class.getName() + " with a null id.");
        }
        EmailTypeDomain domain = repository.findOne(id);

        if (domain == null) {
            throw new EmailTypeResourceNotFoundException("No " + EmailType.class.getName() + " found for id :" + id);
        }

        return domain.toModel();
    }

    public List<EmailType> findAll() {
        List<EmailTypeDomain> domains = repository.findAll();

        return domains.stream().map(EmailTypeDomain::toModel).collect(Collectors.toList());
    }

}
