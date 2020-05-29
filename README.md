# RedditMemesJAVA
[![](https://jitpack.io/v/Blad3Mak3r/RedditMemesJava.svg)](https://jitpack.io/#Blad3Mak3r/RedditMemesJava)

**This package is behind used in production by [HUGE](https://hugebot.net)**

[![](https://i.imgur.com/Jyb6NZC.png)](https://hugebot.net)

# Getting started
**Reddit.getRandomMeme()** returns a **PendingRequest** class which can be asynchronously terminated with **queue()**, **submit()** or sync with **complete()**

### Get a random meme

``getRandomMeme()`` or ``getRandomMeme(string)``.

**Create the PendingRequest object**
```java
PendingRequest request = Reddit.getRandomMeme()
```

**Async Consumer/BiConsumer with ``queue()``**
```java
request.queue((meme) -> {
    System.out.println(meme.toString());
}, (error) -> {
    error.printStackTrace();
})
```

**Async CompletableFuture with ``sumbit()``**
```java
CompletableFuture<RedditMeme> future = request.submit();

future.whenCompleteAsync((meme) -> {
    System.out.println(meme.toString());
}, (throwable) -> {
    throwable.printStackTrace();
})
```

**Sync ``complete()``**
```java
try {
    RedditMeme meme = request.complete();
    System.out.println(meme.toString());
} catch(Exception ex) {
    ex.printStackTrace();
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
