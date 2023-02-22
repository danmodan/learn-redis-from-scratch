package com.learnredisfromscratch.api;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class QuotesCountReport implements HttpHandler {

    private final Set<String> handledRequestMethod;

    public QuotesCountReport() {
        this.handledRequestMethod = Collections.emptySet();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String requestMethod = exchange.getRequestMethod();
        
    }
}