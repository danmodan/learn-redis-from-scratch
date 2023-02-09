package com.learnredisfromscratch.broker;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Event {

    private final Map<String, Object> raw;
    private final String eventName;

    public Event(Map<String, Object> raw) {
        this.raw = raw;
        Objects.requireNonNull(this.eventName = (String) raw.get("eventName"));
    }

    public String getEventName() {
        return eventName;
    }

    public <T> T parse(Function<Map<String, Object>, T> parseFunc) {
        return parseFunc.apply(raw);
    }
}