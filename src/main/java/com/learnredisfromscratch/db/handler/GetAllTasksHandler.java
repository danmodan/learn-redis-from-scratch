package com.learnredisfromscratch.db.handler;

import java.util.List;
import java.util.function.Consumer;

import redis.clients.jedis.Jedis;

public class GetAllTasksHandler implements Consumer<Jedis> {

    private final String listName;
    private final List<String> tasks;

    public GetAllTasksHandler(String listName, List<String> tasks) {
        this.listName = listName;
        this.tasks = tasks;
    }

    @Override
    public void accept(Jedis jedis) {

        var foundTasks = jedis.lrange(listName, 0, -1);
        tasks.addAll(foundTasks);
    }
}
