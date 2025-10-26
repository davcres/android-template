package com.davidcrespo.template.core.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp

@Composable
fun Animation(modifier: Modifier = Modifier) {
    val animation = remember { Animatable(0f) }
    var toggled by remember { mutableStateOf(false) }

    LaunchedEffect(toggled) {
        animation.animateTo(
            targetValue = if (toggled) 1f else 0f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = { it * it * (3 - 2 * it) }
            )
        )
    }

    /* Infinite animation
    LaunchedEffect(Unit) {
        animation.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }*/

    val animationProgress = animation.value

    val startSize = 72.dp
    val endSize = 148.dp
    val startRadius = 8.dp
    val endRadius = 200.dp
    val startColor = Color.Cyan
    val endColor = Color.Magenta

    val size = lerp(startSize, endSize, animationProgress)
    val radius = lerp(startRadius, endRadius, animationProgress)
    val color = androidx.compose.ui.graphics.lerp(startColor, endColor, animationProgress)

    BoxWithConstraints(
        modifier = modifier
    ) {
        // Top-Left → Bottom-Right, keeping the box inside the screen
        val startX = 0.dp
        val startY = 0.dp
        val endX = (this.maxWidth - size).coerceAtLeast(0.dp)
        val endY = (this.maxHeight - size).coerceAtLeast(0.dp)
        val xDp = lerp(startX, endX, animationProgress)
        val yDp = lerp(startY, endY, animationProgress)

        Box(
            modifier = Modifier
                .offset(x = xDp, y = yDp)
                .size(size)
                .background(color, RoundedCornerShape(radius))
                .clickable { toggled = !toggled }
        )
    }
}

@Composable
@Preview
fun AnimationPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Animation(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}
