package com.learnredisfromscratch.db.handler;

import java.util.function.Consumer;

import redis.clients.jedis.Jedis;

public class CreateTaskHandler implements Consumer<Jedis> {

    private final String listName;
    private final String task;

    public CreateTaskHandler(String listName, String task) {
        this.listName = listName;
        this.task = task;
    }

    @Override
    public void accept(Jedis jedis) {

        jedis.rpush(listName, task);
    }
}
