package org.ubp.ent.backend.utils;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Starts the web-context, container is started, but URL aren't deployed.
 */
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class WebApplicationTest extends WebTest {

}
