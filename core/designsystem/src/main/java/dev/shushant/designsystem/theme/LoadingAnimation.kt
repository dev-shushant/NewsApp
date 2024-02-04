package dev.shushant.designsystem.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.shushant.designsystem.R

/**
 * A Composable function that displays a loading animation using a rotating circular progress indicator.
 *
 * @param modifier The modifier for styling and positioning of the loading animation.
 * @param indicatorSize The size of the loading animation indicator.
 * @param circleColors The list of colors used for the circular progress indicator.
 * @param animationDuration The duration of the rotation animation in milliseconds.
 */

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 60.dp,
    circleColors: List<Color> = defaultColors,
    animationDuration: Int = 360
) {

    val infiniteTransition = rememberInfiniteTransition(label = stringResource(id = R.string.loader))

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration, easing = LinearEasing
            )
        ), label = ""
    )

    CircularProgressIndicator(
        modifier = modifier
            .size(size = indicatorSize)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp, brush = Brush.sweepGradient(circleColors), shape = CircleShape
            ), progress = { 1f }, strokeWidth = 1.dp, color = MaterialTheme.colorScheme.background
    )
}

private val defaultColors = listOf(
    Color(0xFF5851D8),
    Color(0xFF833AB4),
    Color(0xFFC13584),
    Color(0xFFE1306C),
    Color(0xFFFD1D1D),
    Color(0xFFF56040),
    Color(0xFFF77737),
    Color(0xFFFCAF45),
    Color(0xFFFFDC80),
    Color(0xFF5851D8)
)