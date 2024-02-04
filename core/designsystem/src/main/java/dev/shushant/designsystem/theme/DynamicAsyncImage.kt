package dev.shushant.designsystem.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter

/**
 * A Composable function that loads and displays an image asynchronously from a given URL.
 *
 * @param imageUrl The URL of the image to be loaded.
 * @param contentDescription The content description for the loaded image.
 * @param modifier The modifier for styling and positioning of the image.
 * @param placeholder The placeholder image to be displayed while the main image is loading.
 */

@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            isLoading = state is Loading
            isError = state is Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            // Display a progress bar while loading
            LoadingAnimation(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Image(
            modifier = if (isError.not()) Modifier.fillMaxSize() else Modifier,
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
        )
    }
}