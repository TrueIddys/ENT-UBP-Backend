package org.ubp.ent.backend.utils;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ubp.ent.backend.EntUbpBackendApplication;
import org.ubp.ent.backend.config.CustomSpringProfiles;
import org.ubp.ent.backend.config.conditional.DatabaseTableList;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 * Created by Anthony on 27/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EntUbpBackendApplication.class})
@ActiveProfiles(profiles = {CustomSpringProfiles.TEST_PROFILE})
abstract class WebTest {

    @Inject
    private EntityManager em;

    @Inject
    private DatabaseTableList tableList;

    @Before
    @Transactional
    public void __cleanDatabase() {
        EntityManager emCopy = em.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = emCopy.getTransaction();
        try {
            transaction.begin();
            emCopy.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;").executeUpdate();
            tableList.getNames().parallelStream().forEach(table -> {
                emCopy.createNativeQuery("TRUNCATE TABLE " + table).executeUpdate();
            });
            emCopy.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.clear();
        }
    }

}
