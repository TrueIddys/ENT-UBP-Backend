package org.ubp.ent.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.ubp.ent.backend.config.condition.LocalProfileCondition;

@SpringBootApplication
public class EntUbpBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntUbpBackendApplication.class, args);
    }

    @Bean
    @Conditional(LocalProfileCondition.class)
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("Embedded-test-database")
                .build();
    }

}
