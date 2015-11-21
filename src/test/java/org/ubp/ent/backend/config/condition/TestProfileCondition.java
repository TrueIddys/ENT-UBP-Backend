package org.ubp.ent.backend.config.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.ubp.ent.backend.config.CustomSpringProfiles;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Created by Anthony on 20/11/2015.
 */
public class TestProfileCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        if (hasTestProfile(environment)) {
            return ConditionOutcome.match("A test profile has been found.");
        }
        return ConditionOutcome.noMatch("No test profiles found.");
    }

    private boolean hasTestProfile(Environment environment) {
        String[] profiles = environment.getActiveProfiles();

        return Arrays.stream(profiles).anyMatch(Predicate.isEqual(CustomSpringProfiles.TEST_PROFILE));
    }

}
