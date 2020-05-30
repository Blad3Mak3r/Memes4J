package com.github.blad3mak3r.memes4j;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


public class Meme {
    private final String id;
    private final String subreddit;
    private final String title;
    private final String author;
    private final String image;
    private final Integer ups;
    private final Integer downs;
    private final Integer score;
    private final Integer comments;
    private final Boolean nsfw;
    private final Long createdUtc;

    /**
     * Create a Meme object
     *
     * @param obj a JSONObject with the raw json content of the Reddit API
     * @throws IllegalArgumentException when the object is empty
     */
    public Meme(JSONObject obj) throws IllegalArgumentException {
        if (obj == null) throw new IllegalArgumentException("Object is empty.");
        id = obj.getString("id");
        subreddit = obj.getString("subreddit");
        title = obj.getString("title");
        author = obj.getString("author");
        image = obj.getString("url");
        ups = obj.getInt("ups");
        downs = obj.getInt("downs");
        score = obj.getInt("score");
        comments = obj.getInt("num_comments");
        nsfw = obj.getBoolean("over_18");
        createdUtc = obj.getLong("created_utc");
    }

    public String getId() {
        return id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImage() {
        return image;
    }

    public Integer getUps() {
        return ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getComments() {
        return comments;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public Long getCreatedUtc() {
        return createdUtc;
    }

    public String getLink() {
        return "https://reddit.com/" + id;
    }

    @Override
    public String toString() {
        return "RedditMeme{" +
                "id='" + id + '\'' +
                ", subreddit='" + subreddit + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                ", ups=" + ups +
                ", downs=" + downs +
                ", score=" + score +
                ", comments=" + comments +
                ", nsfw=" + nsfw +
                ", createdUtc=" + createdUtc +
                '}';
    }

    protected static synchronized Meme buildFromJSON(JSONObject node) throws IllegalArgumentException {
        if (node.getJSONObject("data") == null) throw new IllegalArgumentException();

        JSONObject data = node.getJSONObject("data");

        JSONArray posts = data.getJSONArray("children");

        for (int i = 0; i < 25; i++) {
            int random = new Random().nextInt(posts.length());

            JSONObject sel = posts.getJSONObject(random).getJSONObject("data");
            if (isImage(sel.getString("url"))) {
                return new Meme(sel);
            }
        }

        throw new IllegalArgumentException();
    }

    private static boolean isImage(@Nullable String str) {
        if (str == null) return false;
        return str.endsWith(".png") || str.endsWith(".jpg") || str.endsWith(".jpeg") || str.endsWith(".gif");
    }
}
