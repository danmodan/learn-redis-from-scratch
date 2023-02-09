package com.learnredisfromscratch.service.task;

import com.learnredisfromscratch.db.JediClient;
import com.learnredisfromscratch.db.handler.CreateTaskHandler;

public class CreateTaskService {

    private final JediClient jediClient;

    public CreateTaskService(JediClient jediClient) {
        this.jediClient = jediClient;
    }

    public void createTask(String listName, String task) {

        jediClient.onJedis(new CreateTaskHandler(listName, task));
    }
}
