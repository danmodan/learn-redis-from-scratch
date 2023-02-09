package com.learnredisfromscratch.broker;

public interface Publisher {

    boolean addSubscriber(Subscriber subscriber);

    boolean removeSubscriber(Subscriber subscriber);
}
