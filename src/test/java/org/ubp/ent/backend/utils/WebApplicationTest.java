package org.ubp.ent.backend.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.ubp.ent.backend.EntUbpBackendApplication;
import org.ubp.ent.backend.config.CustomSpringProfiles;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Starts the web-context, container is started, but URL aren't deployed.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EntUbpBackendApplication.class})
@WebAppConfiguration
@ActiveProfiles(profiles = {CustomSpringProfiles.TEST_PROFILE})
public abstract class WebApplicationTest {

    @Inject
    private PlatformTransactionManager transactionManager;

    private TransactionStatus currentTransactionStatus;

    /**
     * Isolate the test modification in a transaction.
     * The transaction will be rolled back after the test.
     */
    @Before
    public final void _setUp() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        currentTransactionStatus = transactionManager.getTransaction(def);
    }

    /**
     * Rollback the transaction to cancel test modifications.
     */
    @After
    public final void _tearDown() {
        transactionManager.rollback(currentTransactionStatus);
    }

}
