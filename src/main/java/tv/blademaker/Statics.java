package tv.blademaker;

import java.util.List;

public class Statics {
    protected static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";

    protected static final String BASE_URL = "https://www.reddit.com/r/%s/hot/.json?count=100";

    protected static String getBaseUrl(String subReddit) {
        return String.format(BASE_URL, subReddit);
    }

    protected static final List<String> DEFAULT_REDDITS = List.of("memes", "dankmemes", "meirl");
}
