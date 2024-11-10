package com.stub.rest_sqlite.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stub.rest_sqlite.entity.BugFact;
import com.stub.rest_sqlite.repository.BugFactRepository;
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(BugFactRepository repository) {
        return _ -> {
            log.info("Preloading " + repository.save(new BugFact("One day you will have to answer for your actions.")));
            log.info("Preloading " + repository.save(new BugFact("Spiders are scary!")));
        };
    }
}
