package tv.blademaker;

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

    public void queue(Consumer<RedditMeme> meme, Consumer<Throwable> error) {
        cf.whenCompleteAsync((r, t) -> {
            if (t != null) error.accept(t);
            else if (!r.isSuccess()) error.accept(new Exception(r.getStatusText()));
            else {
                try {
                    RedditMeme m = RedditMeme.buildFromJSON(r.getBody().getObject());
                    meme.accept(m);
                } catch (IllegalArgumentException ex) {
                    error.accept(ex);
                }
            }
        });
    }

    public CompletableFuture<RedditMeme> submit() {
        CompletableFuture<RedditMeme> future = new CompletableFuture<>();

        cf.whenCompleteAsync((r, t) -> {
            if (t != null) future.completeExceptionally(t);
            else if (!r.isSuccess()) future.completeExceptionally(new Exception(r.getStatusText()));
            else {
                try {
                    RedditMeme meme = RedditMeme.buildFromJSON(r.getBody().getObject());
                    future.complete(meme);
                } catch (IllegalArgumentException ex) {
                    future.completeExceptionally(ex);
                }
            }
        });

        return future;
    }

    public RedditMeme complete() throws ExecutionException, InterruptedException {
        HttpResponse<JsonNode> response = cf.get();

        return RedditMeme.buildFromJSON(response.getBody().getObject());
    }
}
