package com.stub.rest_sqlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stub.rest_sqlite.entity.BugFact;

public interface BugFactRepository extends JpaRepository<BugFact, Integer> {
    BugFact findById(int id);

    @Query(value = "SELECT * FROM bug_fact ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    BugFact findRandomBugFact();
}
