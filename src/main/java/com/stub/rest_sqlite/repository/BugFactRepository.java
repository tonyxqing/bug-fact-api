package com.stub.rest_sqlite.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.stub.rest_sqlite.entity.BugFact;

public interface BugFactRepository extends CrudRepository<BugFact, Integer> {
    BugFact findById(int id);
    
    List<BugFact> findAll();
}
