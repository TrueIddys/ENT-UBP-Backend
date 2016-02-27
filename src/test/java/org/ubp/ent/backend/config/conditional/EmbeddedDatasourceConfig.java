package org.ubp.ent.backend.config.conditional;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.ubp.ent.backend.config.conditional.condition.TestProfileCondition;

/**
 * Created by Anthony on 20/11/2015.
 */
@Configuration
@SuppressWarnings("unused")
public class EmbeddedDatasourceConfig {

    @Bean
    @Conditional(TestProfileCondition.class)
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("Embedded-test-database")
                .build();
    }

    @Bean
    @Conditional(TestProfileCondition.class)
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
