package com.learnredisfromscratch.service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learnredisfromscratch.client.FuturamaClient;
import com.learnredisfromscratch.client.Quote;
import com.learnredisfromscratch.repository.QuoteRepository;

public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final FuturamaClient futuramaClient;

    public QuoteService(
            QuoteRepository quoteRepository,
            FuturamaClient futuramaClient) {
        this.quoteRepository = quoteRepository;
        this.futuramaClient = futuramaClient;
    }

    public List<String> getAllQuotes(String character) {

        return CompletableFuture
                .supplyAsync(() -> quoteRepository.getAllQuotes(character))
                .thenApply(cached -> {

                    if (cached != null && !cached.isEmpty()) {

                        return cached;
                    }

                    CompletableFuture<List<String>> responseFuture = futuramaClient
                            .getAllQuotesByCharacter(character)
                            .thenCompose(list -> {
                                List<String> collect = list.stream().map(Quote::getQuote).collect(Collectors.toList());
                                if(collect.isEmpty()) {
                                    return CompletableFuture.failedFuture(new NoSuchElementException(character));
                                }
                                return CompletableFuture.completedFuture(collect);
                            });

                    responseFuture.whenCompleteAsync((l, t) -> {

                        if(t != null) {
                            return;
                        }

                        quoteRepository.addQuotes(character, 10, l);
                    });

                    return responseFuture.join();
                })
                .join();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Vai {

        private String quote;

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }
    }

    public List<String> getQuotes(String character, int start, int size) {

        return quoteRepository.getQuotes(character, start, size);
    }

    public void addQuotes(String character, Collection<String> quotes) {

        quoteRepository.addQuotes(character, quotes);
    }
}
