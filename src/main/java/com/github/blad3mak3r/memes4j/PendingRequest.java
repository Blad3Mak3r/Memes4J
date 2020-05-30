package com.github.blad3mak3r.memes4j;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class PendingRequest {

    private final CompletableFuture<HttpResponse<JsonNode>> cf;

    PendingRequest(CompletableFuture<HttpResponse<JsonNode>> httpResponseCompletableFuture) {
        cf = httpResponseCompletableFuture;
    }

    public void queue(Consumer<Meme> meme, Consumer<Throwable> error) {
        cf.whenCompleteAsync((r, t) -> {
            if (t != null) error.accept(t);
            else if (!r.isSuccess()) error.accept(new Exception(r.getStatusText()));
            else {
                try {
                    Meme m = Meme.buildFromJSON(r.getBody().getObject());
                    meme.accept(m);
                } catch (IllegalArgumentException ex) {
                    error.accept(ex);
                }
            }
        });
    }

    public CompletableFuture<Meme> submit() {
        CompletableFuture<Meme> future = new CompletableFuture<>();

        cf.whenCompleteAsync((r, t) -> {
            if (t != null) future.completeExceptionally(t);
            else if (!r.isSuccess()) future.completeExceptionally(new Exception(r.getStatusText()));
            else {
                try {
                    Meme meme = Meme.buildFromJSON(r.getBody().getObject());
                    future.complete(meme);
                } catch (IllegalArgumentException ex) {
                    future.completeExceptionally(ex);
                }
            }
        });

        return future;
    }

    public Meme complete() throws ExecutionException, InterruptedException {
        HttpResponse<JsonNode> response = cf.get();

        return Meme.buildFromJSON(response.getBody().getObject());
    }
}
