package com.learnredisfromscratch.api;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * CharacterQuotes
 */
public class CharacterQuotes implements HttpHandler {

    private final Set<String> handledRequestMethod;

    public CharacterQuotes() {
        this.handledRequestMethod = Collections.emptySet();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        System.out.println(exchange.getRequestURI().getPath());
    }
}