package com.learnredisfromscratch.repository;

import java.util.Collection;
import java.util.List;

import com.learnredisfromscratch.repository.db.DbResource;

public class QuoteRepository {

    private static final String ROOT = "quote:";
    private final DbResource dbResource;

    public QuoteRepository(DbResource dbResource) {
        this.dbResource = dbResource;
    }

    public List<String> getAllQuotes(String character) {

        return dbResource.getAllList(ROOT + character);
    }

    public List<String> getQuotes(String character, int start, int size) {

        return dbResource.getSubList(ROOT + character, start, size);
    }

    public void addQuotes(String character, Collection<String> quotes) {

        dbResource.addItens(ROOT + character, quotes.toArray(String[]::new));
    }

    public void addQuotes(String character, int seconds, Collection<String> quotes) {

        dbResource.addItens(ROOT + character, seconds, quotes.toArray(String[]::new));
    }
}
