package com.learnredisfromscratch.util;

@FunctionalInterface
public interface ThrowableConsumer<T> {

    void accept(T t) throws Exception;
}
