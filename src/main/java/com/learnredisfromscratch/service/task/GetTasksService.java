package com.learnredisfromscratch.service.task;

import java.util.ArrayList;
import java.util.List;

import com.learnredisfromscratch.db.JediClient;
import com.learnredisfromscratch.db.handler.GetAllTasksHandler;

public class GetTasksService {

    private final JediClient jediClient;

    public GetTasksService(JediClient jediClient) {
        this.jediClient = jediClient;
    }

    public List<String> getAllTask(String listName) {

        List<String> list = new ArrayList<>();
        jediClient.onJedis(new GetAllTasksHandler(listName, list));

        return list;
    }
}
