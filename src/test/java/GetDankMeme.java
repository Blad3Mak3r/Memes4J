import tv.blademaker.Reddit;
import tv.blademaker.RedditMeme;

import java.util.concurrent.ExecutionException;

public class GetDankMeme {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RedditMeme meme = Reddit.getRandomMeme("dankmemes").complete();

        System.out.println(meme.toString());
    }
}
