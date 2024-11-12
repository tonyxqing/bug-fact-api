package com.stub.rest_sqlite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public List<BugFact> removeAllBugFacts() {
        repository.deleteAll();
        return repository.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<BugFact> removeBugFactById(@PathVariable int id) {
        repository.deleteById(id);
        return repository.findAll();
    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateBugFactById(@RequestBody BugFact bugFact, @PathVariable Integer id) {
        if (repository.findById(id).isPresent()) {
            bugFact.setId(id);
            BugFact saved = repository.save(bugFact);
            return ResponseEntity.status(HttpStatus.OK).body(saved);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fact id not found");
    }
}
