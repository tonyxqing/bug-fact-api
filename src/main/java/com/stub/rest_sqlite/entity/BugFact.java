package com.stub.rest_sqlite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BugFact {

    @Id
    @GeneratedValue
    private int id;
    private String fact;

    protected BugFact() {}

    public BugFact(String fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return String.format("BugFact[id = %d, fact = '%s']", id, fact);
    }

    public int getId() {
        return id;
    }

    public String getFact() {
        return fact;
    }

    public void setId(int id) {
        this.id = id;
    }
}
