package com.learnredisfromscratch.repository;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.learnredisfromscratch.repository.db.DbResource;
import com.learnredisfromscratch.repository.db.ListDbResource;

public class QuoteRepositoryTest {

    private QuoteRepository quoteRepository;
    private Map<String, List<String>> db;

    @Before
    public void setup() {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        db = new HashMap<>();
        db.put("Bender", list);

        DbResource dbResource = new ListDbResource(db);
        quoteRepository = new QuoteRepository(dbResource);
    }

    @Test
    public void giver_character_than_return_all_quotes() {

        var actual = quoteRepository.getAllQuotesByCharacter("Bender");
        var expected = db.get("Bender");
        
        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void giver_character_than_return_quotes() {

        var actual = quoteRepository.getQuotesByCharacter("Bender", 1, 6);
        var expected = db.get("Bender").subList(1, 7);
        
        assertTrue(actual.containsAll(expected));
    }
}
