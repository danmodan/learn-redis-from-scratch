package com.learnredisfromscratch.broker.listener.task;

import com.learnredisfromscratch.broker.Event;
import com.learnredisfromscratch.broker.Subscriber;
import com.learnredisfromscratch.broker.event.task.CreateTaskEvent;
import com.learnredisfromscratch.service.task.CreateTaskService;

public class CreateTaskEventListener implements Subscriber {

    private final CreateTaskService service;

    public CreateTaskEventListener(CreateTaskService service) {
        this.service = service;
    }

    @Override
    public void onEvent(Event event) {

        if(!canHandle(event.getEventName())) {
            return;
        }

        CreateTaskEvent data = event.parse(new CreateTaskEvent.Parser());
        String listName = data.getListName();
        String task = data.getTask();
        service.createTask(listName, task);
    }

    private boolean canHandle(String eventName) {
        return "CREATE_TASK".equals(eventName);
    }
}
