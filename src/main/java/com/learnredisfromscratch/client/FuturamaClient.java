package com.learnredisfromscratch.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.learnredisfromscratch.util.ExecutorUtil;
import com.learnredisfromscratch.util.JsonUtil;

public class FuturamaClient {

    private static final String BASE_URI = "https://futurama-api.fly.dev";

    public CompletableFuture<List<Quote>> getAllQuotesByCharacter(String character) {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(createURI(BASE_URI + "/api/characters/" + character))
                .GET()
                .setHeader("Accept", "application/json")
                .timeout(Duration.of(3, ChronoUnit.SECONDS))
                .build();

        return HttpClient
                .newBuilder()
                .executor(ExecutorUtil.getExecutorService())
                .followRedirects(Redirect.ALWAYS)
                .build()
                .sendAsync(request, BodyHandlers.ofString())
                .thenApplyAsync(httpResponse -> {

                    if (200 != httpResponse.statusCode()) {

                        throw new RuntimeException(
                                String.format("status code %s - %s", httpResponse.statusCode(), httpResponse.body()));
                    }

                    return JsonUtil.readerForListOf(Quote.class, httpResponse.body());
                });
    }

    private URI createURI(String str) {

        try {

            return new URI(str);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
