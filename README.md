# Memes4J
[![](https://jitpack.io/v/Blad3Mak3r/Memes4J.svg)](https://jitpack.io/#Blad3Mak3r/Memes4J)

**This package is behind used in production by [HUGE](https://hugebot.net)**

[![](https://i.imgur.com/Jyb6NZC.png)](https://hugebot.net)

- - -

You can also get this package for other programming languages:
| Packages                                               |
|--------------------------------------------------------|
| [Java](https://github.com/Blad3Mak3r/Memes4J)  |
| [TS/JS](https://github.com/Blad3Mak3r/RedditMemes)     |

# Getting started
This package requires **Java 9** or higher version.

The static method **Memes4J.getRandomMeme()** returns a **PendingRequest** class which can be asynchronously terminated with **queue()**, **submit()** or sync with **complete()**

### Get a random meme

``getRandomMeme()`` or ``getRandomMeme(string)``.

**Create the PendingRequest object**
```java
PendingRequest request = Memes4J.getRandomMeme()
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
CompletableFuture<Meme> future = request.submit();

future.whenCompleteAsync((meme) -> {
    System.out.println(meme.toString());
}, (throwable) -> {
    throwable.printStackTrace();
})
```

**Sync ``complete()``**
```java
try {
    Meme meme = request.complete();
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
        implementation 'com.github.Blad3Mak3r:Memes4J:0.1.1'
}
```

***With Maven***
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.Blad3Mak3r</groupId>
    <artifactId>Memes4J</artifactId>
    <version>0.1.1</version>
</dependency>
```
