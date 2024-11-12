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
        if (repository.count() == 0) {
            return _ -> {
                log.info("Repository is Empty");
                log.info("Preloading " + repository.save(new BugFact(
                        "One day you will have to answer for your actions and God may not be so merciful.")));
                log.info("Preloading " + repository
                        .save(new BugFact("One day the voices will cease.\r\n It'll be the day the voices stopped.")));
                log.info("Preloading " + repository.save(new BugFact(
                        "You will realize, too late in your career, that you picked the wrong programming language.\r\n By that point, it'll be too late to do anything else.\r\n Turns out you can't teach an old dog new tricks.")));
            };
        }

        return _ -> {
            log.info("Repository contains facts");
        };

    }
}
