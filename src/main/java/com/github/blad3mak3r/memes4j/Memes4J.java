package com.github.blad3mak3r.memes4j;

import kong.unirest.*;

import java.util.Random;

public class Memes4J {

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
