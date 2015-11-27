package org.ubp.ent.backend.utils;

import org.springframework.test.annotation.DirtiesContext;

/**
 * Created by Anthony on 27/11/2015.
 */
@org.springframework.boot.test.WebIntegrationTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public abstract class WebIntegrationTest extends  WebTest {
}
