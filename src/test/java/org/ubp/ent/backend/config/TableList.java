package org.ubp.ent.backend.config;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anthony on 06/01/2016.
 */
@Component
public class TableList {

    @Inject
    private EntityManagerFactory emf;

    private Set<String> names = new HashSet<>();

    public TableList() {
    }

    @PostConstruct
    public void populateTableList() {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        for (final ClassMetadata metadata : sessionFactory.getAllClassMetadata().values()) {
            final String tableName = ((AbstractEntityPersister) metadata).getTableName();
            if (tableName != null) {
                names.add(tableName);
            }
        }
    }

    public Collection<String> getNames() {
        return names;
    }

}
