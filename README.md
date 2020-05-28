# RedditMemesJAVA
[![](https://jitpack.io/v/Blad3Mak3r/RedditMemesJava.svg)](https://jitpack.io/#Blad3Mak3r/RedditMemesJava)

# Getting started
**Reddit.getRandomMeme()** returns a **PendingRequest** class which can be asynchronously terminated with **queue()**, **submit()** or sync with **complete()**

### Get a random meme

``getRandomMeme()`` or ``getRandomMeme(string)``.

```java
import tv.blademaker.PendingRequest;
import tv.blademaker.Reddit;
import tv.blademaker.RedditMeme;
import java.util.concurrent.CompletableFuture;

class Main {
    // Create the request
    PendingRequest rq = Reddit.getRandomMeme()

    // Use Async consumers
    rq.queue((meme) -> {
        System.out.println(meme.toString());
    }, (error) -> {
        error.printStackTrace();
    })
    
    // Get a CompletableFuture
    CompletableFuture<RedditMeme> future = rq.submit();
    
    future.whenCompleteAsync((meme) -> {
        System.out.println(meme.toString());
    }, (throwable) -> {
        throwable.printStackTrace();
    })

    // Sync return
    try {
        RedditMeme meme = rq.complete();
        System.out.println(meme.toString());
    } catch(Exception ex) {
        ex.printStackTrace();
    }
}
```

## How to download
***With Gradle***
```java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```java
dependencies {
        implementation 'com.github.Blad3Mak3r:RedditMemesJava:v0.1.0'
}
```

***With Maven***
```java
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```java
<dependency>
    <groupId>com.github.Blad3Mak3r</groupId>
    <artifactId>RedditMemesJava</artifactId>
    <version>v0.1.0</version>
</dependency>
```