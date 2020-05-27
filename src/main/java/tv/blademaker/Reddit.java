package tv.blademaker;

import kong.unirest.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Reddit {

    private static final UnirestInstance unirest = new UnirestInstance(
            new Config().addDefaultHeader("User-Agent", Statics.USER_AGENT)
    );

    public static PendingRequest getRandomMeme(String subreddit) {
        CompletableFuture<RedditMeme> future = new CompletableFuture<>();

        return new PendingRequest(unirest.get(Statics.getBaseUrl(subreddit)).asJsonAsync());
    }
}
