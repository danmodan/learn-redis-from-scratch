package com.learnredisfromscratch.broker;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Broker implements Publisher {

    private static final int MAX_QUEUE_SIZE = 3;
    private final Set<Subscriber> subscribers = new HashSet<>();
    private final Queue<Event> eventStorage = new ArrayDeque<>();
    private boolean readyToBroadcast = false;
    ExecutorService exec = Executors.newFixedThreadPool(4,
        new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

    @Override
    public boolean addSubscriber(Subscriber subscriber) {
        return subscribers.add(subscriber);
    }

    @Override
    public boolean removeSubscriber(Subscriber subscriber) {
        return subscribers.remove(subscriber);
    }

    public synchronized void addEvent(Event event) throws InterruptedException {

        while(eventStorage.size() + 1 >= MAX_QUEUE_SIZE) {
            wait();
        }

        eventStorage.add(event);
        notifyAll();
    }

    public synchronized void readyToBroadcast() {

        readyToBroadcast = true;
        notifyAll();
    }

    public synchronized void broadcastEvents() throws InterruptedException {

        while(
            eventStorage.isEmpty() ||
            !readyToBroadcast
        ) {
            readyToBroadcast = false;
            return;
        }

        Event event = null;
        while((event = eventStorage.poll()) != null) {

            for(var subscriber : subscribers) {
            vai(subscriber, event);
            }
        }
    }

    private void vai(Subscriber subscriber, Event event) {

        exec.submit(() -> subscriber.onEvent(event));
    }
}
