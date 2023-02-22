package com.learnredisfromscratch;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.learnredisfromscratch.api.CharacterQuotes;
import com.learnredisfromscratch.api.QuotesCountReport;
import com.sun.net.httpserver.HttpServer;

public class App {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.setExecutor(Executors.newFixedThreadPool(3));
        server.createContext("/quote", new CharacterQuotes());
        server.createContext("/report", new QuotesCountReport());
        server.start();
    }
}