# compose-duck
This is a UI library for Jetpack Compose that displays random duck jpg or gif using Random Duck Api.
<img src="./duck.gif" alt="duck">
# Download
```
repositories {
  mavenCentral()
}

dependencies {
  implementation 'io.github.sanao1006:compose-duck:0.5.0'
}
```
# Usage
This library provides two functions `RandomDuckJpg()` and `RandomDuckGif()`.  
RandomDuckJpg() displays a random jpg of a duck obtained from the Random Duck API, and RandomDuckGif() similarly displays a gif.  

Also, both functions wrap AsyncImage in coil. Therefore, by referring to AsyncImage, it is possible to customize the obtained image in detail.

This is sample code.
```kotlin
@Composable
fun Sample(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(128.dp), contentAlignment = Alignment.Center) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(3) {
                SampleItem()
            }
        }
    }
}

@Composable
fun SampleItem() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        RandomDuckGif(modifier = Modifier.size(128.dp))
        RandomDuckJpg(modifier = Modifier.size(128.dp))
    }
}
```
Result:  
<img src="./result.gif" alt="duck" width="300">

