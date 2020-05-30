import com.github.blad3mak3r.memes4j.Memes4J;
import com.github.blad3mak3r.memes4j.Meme;

import java.util.concurrent.ExecutionException;

public class GetDankMeme {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Meme meme = Memes4J.getRandomMeme("dankmemes").complete();

        System.out.println(meme.toString());

        Meme meme2 = Memes4J.getRandomMeme().complete();

        System.out.println(meme2.toString());
    }
}
