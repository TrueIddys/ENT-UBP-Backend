package org.ubp.ent.backend.config.conditional;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.ubp.ent.backend.config.conditional.condition.LocalProfileCondition;

/**
 * Created by Anthony on 06/01/2016.
 */
@Configuration
@SuppressWarnings("unused")
public class LocalEmbeddedDatabase {

    /**
     * Instead of using the applications.properties that try to connect to the real database, we start an Embedded
     * databases for development purpose. Only when {@link LocalProfileCondition} is satisfied.
     *
     * @return An {@link EmbeddedDatabase} instance.
     */
    @Bean
    @Conditional(value = {LocalProfileCondition.class})
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("Embedded-test-database")
                .build();
    }

    @Bean
    @Conditional(value = {LocalProfileCondition.class})
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
