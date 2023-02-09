package com.learnredisfromscratch.db.handler;

import java.util.function.Consumer;

import redis.clients.jedis.Jedis;

public class RemoveTaskHandler implements Consumer<Jedis> {

    private final String listName;
    private final int index;

    public RemoveTaskHandler(String listName, int index) {
        this.listName = listName;
        this.index = index;
    }

    @Override
    public void accept(Jedis jedis) {

        var allTasks = jedis.lrange(listName, 0, -1);

        if (allTasks.size() <= index) {
            return;
        }

        var toRemove = allTasks.get(index);
        jedis.lrem(listName, 0, toRemove);
    }
}