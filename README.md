![alt text](https://github.com/Oyzuu/PatternPlaceholder-Android/blob/master/app/src/main/res/mipmap-xxxhdpi/patternplaceholder-title.png "Pattern Placeholder for Android")
A bitmap generator, for Android, with numerous tiling options.

## Features
* Pseudo random bitmap generation
* Synchronous and asynchronous generation
* Caching for huge and / or dense images
* All the material colors at hand with swatches or individual color getters

## How-to
Instantiate a new Builder instance with a context and generate. It will, by default, generate a 3 by 3 random gray mozaic.
```java
Bitmap bitmap = new PatternPlaceholder.Builder(this).generate();
```

Specify the number of tiles, bitmap size, overdrawn text, etc...
```java
Bitmap bitmap = new PatternPlaceholder.Builder(this)
                .setSize(300, 300)
                .setTilesPerSide(3)
                .setPatternType(PatternPlaceholder.PatternType.RANDOM_TRIANGLES)
                .setText("User")
                .setTextColor(Color.WHITE)
                .setTextAlign(PatternPlaceholder.TextAlign.LOWER_RIGHT)
                .generate();
```

Use [any material design color](https://material.google.com/style/color.html) individually or get corresponding swatch with MaterialColor.getColorForValue(int, int) and getSwatch(int)

For asynchronous loading, use generate(Context) or generate(ImageView). 
With a context provided as listener, onGenerated(Bitmap) will be called on completion.
```java
new PatternPlaceholder.Builder(this)
                // the complete swatch from 50 to 900
                .setPalette(MaterialColor.getSwatch(MaterialColor.Color.LIGHT_BLUE))
                .generate(yourImageView); 
                // or generate(yourContext)
```

Or use RandomColor for various random coloring (i.e. : light greys,  dark greys or any randomized color)
```java
new PatternPlaceholder.Builder(this)
                // ALL, LIGHT_GREY, MEDIUM_GREY, DARK_GREY, GREY
                .setColorGenerationType(RandomColor.ColorType.ALL) 
                .generate(yourImageView);
```

## Installation
Via Maven...
```xml
<dependency>
  <groupId>be.omnuzel.patternplaceholder</groupId>
  <artifactId>patternplaceholder</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
... or gradle, by adding this repository in your project ``build.gradle``
```gradle
maven {
  url 'https://dl.bintray.com/oyzuu/maven/'
}
```
and this in your app ``build.gradle``
```gradle
compile 'be.omnuzel.patternplaceholder:patternplaceholder:1.0.0'
```

