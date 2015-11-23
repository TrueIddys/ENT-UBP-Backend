package org.ubp.ent.backend.utils;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.ubp.ent.backend.EntUbpBackendApplication;
import org.ubp.ent.backend.config.CustomSpringProfiles;

/**
 * Starts the web-context, container is started, but URL aren't deployed.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EntUbpBackendApplication.class})
@WebAppConfiguration
@ActiveProfiles(profiles = {CustomSpringProfiles.TEST_PROFILE})
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class WebApplicationTest {

}
