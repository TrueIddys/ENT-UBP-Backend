package org.ubp.ent.backend.utils;

import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Starts the web-context, container is started, but URL aren't deployed.
 */
@WebAppConfiguration
public abstract class WebApplicationTest extends WebTest {

}
