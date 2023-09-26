package io.github.duck.compose

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun RandomDuckGif(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    transform: (AsyncImagePainter.State) -> AsyncImagePainter.State = AsyncImagePainter.DefaultTransform,
    onState: ((AsyncImagePainter.State) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    target: (ImageRequest.Builder.() -> ImageRequest.Builder)? = null,
) {
    val imageRequestBuilder = ImageRequest.Builder(LocalContext.current)
        .data("https://random-d.uk/api/v2/randomimg?type=gif")

    target?.let { it(imageRequestBuilder) }
    val imageRequest = imageRequestBuilder.build()

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components { add(if (SDK_INT >= 28) ImageDecoderDecoder.Factory() else GifDecoder.Factory()) }
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        modifier = modifier,
        transform = transform,
        onState = onState,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
    )
}

