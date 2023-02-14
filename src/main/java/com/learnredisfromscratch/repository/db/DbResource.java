package com.learnredisfromscratch.repository.db;

import java.util.List;

public interface DbResource {

    List<String> getSubList(String listName, int start, int size);

    List<String> getAllList(String listName);

    void addItens(String listName, String... itens);
}
