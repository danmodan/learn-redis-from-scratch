package com.learnredisfromscratch.broker;

public interface Subscriber {

    void onEvent(Event event);
}