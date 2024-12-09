package com.stub.rest_sqlite.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/add")
    public BugFact addBugFact(@RequestBody BugFact bugFact) {
        return repository.save(bugFact);
    }

    @DeleteMapping("/remove-all")
    public void removeAllBugFacts() {
        repository.deleteAll();
    }

    @DeleteMapping("/remove/{id}")
    public void removeBugFactById(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateBugFactById(@RequestBody BugFact bugFact, @PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            bugFact.setId(id);
            BugFact saved = repository.save(bugFact);
            return ResponseEntity.ok(saved);
        }

        return ResponseEntity.badRequest().body(String.format("BugFact ID: %d is not valid", id));
    }
}
