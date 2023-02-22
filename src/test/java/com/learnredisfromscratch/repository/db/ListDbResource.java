package com.learnredisfromscratch.repository.db;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ListDbResource implements DbResource {

    private final Map<String, List<String>> db;

    public ListDbResource(Map<String, List<String>> db) {
        this.db = db;
    }

    @Override
    public List<String> getSubList(String listName, int start, int size) {

        List<String> list = Optional.ofNullable(db.get(listName)).orElseThrow();
        int stop = start + size;
        stop = Math.min(stop, list.size());
        return list.subList(start, stop);
    }

    @Override
    public List<String> getAllList(String listName) {

        return Optional.ofNullable(db.get(listName)).orElseThrow();
    }

    @Override
    public void addItens(String listName, String... itens) {

        List<String> list = Optional.ofNullable(db.get(listName)).orElseThrow();
        list.addAll(Arrays.asList(itens));
    }

    @Override
    public void addItens(String listName, int expirationSeconds, String... itens) {
        // TODO Auto-generated method stub
        
    }
}
