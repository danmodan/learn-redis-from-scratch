package com.learnredisfromscratch.repository;

import java.util.Collection;
import java.util.List;

import com.learnredisfromscratch.repository.db.DbResource;

public class QuoteRepository {

    private final DbResource dbResource;

    public QuoteRepository(DbResource dbResource) {
        this.dbResource = dbResource;
    }

    public List<String> getAllQuotes(String character) {

        return dbResource.getAllList(character);
    }

    public List<String> getQuotes(String character, int start, int size) {

        return dbResource.getSubList(character, start, size);
    }

    public void addQuotes(String character, Collection<String> quotes) {

        return dbResource.getSubList(character, start, size);
    }
}
