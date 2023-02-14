package com.learnredisfromscratch.service.task;

import com.learnredisfromscratch.repository.db.JediClient;
import com.learnredisfromscratch.repository.db.handler.RemoveTaskHandler;

public class RemoveTaskService {

    private final JediClient jediClient;

    public RemoveTaskService(JediClient jediClient) {
        this.jediClient = jediClient;
    }

    public void removeTask(String listName, int taskIndex) {

        jediClient.onJedis(new RemoveTaskHandler(listName, taskIndex));
    }
}
