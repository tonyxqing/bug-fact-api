package com.stub.rest_sqlite.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stub.rest_sqlite.entity.BugFact;
import com.stub.rest_sqlite.repository.BugFactRepository;

@RestController
@RequestMapping("bug-facts")
public class BugFactController {
    private final BugFactRepository repository;
    
    public BugFactController(BugFactRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/{id}")
    public BugFact getBugFact(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/all") 
    public List<BugFact> getAllBugFacts() {
        return repository.findAll();
    }
}
