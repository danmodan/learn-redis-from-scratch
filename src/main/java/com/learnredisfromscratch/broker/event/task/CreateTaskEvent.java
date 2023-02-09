package com.learnredisfromscratch.broker.event.task;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class CreateTaskEvent {

    private final String listName;
    private final String task;

    public CreateTaskEvent(String listName, String task) {
        this.listName = listName;
        this.task = task;
    }

    public String getListName() {
        return listName;
    }

    public String getTask() {
        return task;
    }

    public static class Parser implements Function<Map<String, Object>, CreateTaskEvent> {

        @Override
        public CreateTaskEvent apply(Map<String, Object> raw) {

            String listName = null;
            String task = null;

            Objects.requireNonNull(listName = (String) raw.get("list_name"));
            Objects.requireNonNull(task = (String) raw.get("task"));
            return new CreateTaskEvent(listName, task);
        }
    }
}
