package org.ubp.ent.backend.utils;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ubp.ent.backend.EntUbpBackendApplication;
import org.ubp.ent.backend.config.CustomSpringProfiles;

/**
 * Created by Anthony on 27/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EntUbpBackendApplication.class})
@ActiveProfiles(profiles = {CustomSpringProfiles.TEST_PROFILE})
class WebTest {
}
