package tv.blademaker;

import kong.unirest.*;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class Reddit {

    private static final UnirestInstance unirest = new UnirestInstance(
            new Config().addDefaultHeader("User-Agent", Statics.USER_AGENT)
    );

    public static PendingRequest getRandomMeme(String subreddit) {
        return new PendingRequest(unirest.get(Statics.getBaseUrl(subreddit)).asJsonAsync());
    }

    public static PendingRequest getRandomMeme() {
        int random = new Random().nextInt(Statics.DEFAULT_REDDITS.size());
        return new PendingRequest(unirest.get(Statics.getBaseUrl(Statics.DEFAULT_REDDITS.get(random))).asJsonAsync());
    }
}
