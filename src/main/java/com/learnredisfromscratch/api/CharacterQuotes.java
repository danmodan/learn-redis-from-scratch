package com.learnredisfromscratch.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.learnredisfromscratch.client.FuturamaClient;
import com.learnredisfromscratch.repository.QuoteRepository;
import com.learnredisfromscratch.repository.db.JedisDbResource;
import com.learnredisfromscratch.repository.db.JedisPoolFactory;
import com.learnredisfromscratch.service.QuoteService;
import com.learnredisfromscratch.util.JsonUtil;
import com.learnredisfromscratch.util.ThrowableConsumer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * CharacterQuotes
 */
public class CharacterQuotes implements HttpHandler {

    private final Map<String, ThrowableConsumer<HttpExchange>> handlerByMethod;
    private final QuoteService quoteService;

    public CharacterQuotes() {
        this.quoteService = new QuoteService(new QuoteRepository(new JedisDbResource(JedisPoolFactory.getJedisPool())),
                new FuturamaClient());
        this.handlerByMethod = Map.of("GET", this::get);
    }

    private void get(HttpExchange exchange) throws Exception {
        try {

            String[] queryParams = exchange
                    .getRequestURI()
                    .getQuery()
                    .split("=");

            Map<String, String> paramsMap = new HashMap<>();

            for (var i = 0; i + 1 < queryParams.length; i += 2) {

                paramsMap.put(queryParams[i], queryParams[i + 1]);
            }

            String character = paramsMap.get("character");

            if (character == null) {

                exchange.sendResponseHeaders(400, 0);
                return;
            }

            List<String> quotes = quoteService.getAllQuotes(character);
            byte[] response = JsonUtil.writeObject(quotes);
            exchange.sendResponseHeaders(200, response.length);
            exchange.getResponseBody().write(response);
        } catch (Exception e) {
            e.printStackTrace();

            if (e.getCause() instanceof NoSuchElementException) {

                exchange.sendResponseHeaders(404, 0);
                return;
            }

            exchange.sendResponseHeaders(500, 0);
        } finally {
            exchange.close();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            String requestMethod = exchange.getRequestMethod();

            handlerByMethod
                    .getOrDefault(requestMethod, exc -> exc.sendResponseHeaders(405, 0))
                    .accept(exchange);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}