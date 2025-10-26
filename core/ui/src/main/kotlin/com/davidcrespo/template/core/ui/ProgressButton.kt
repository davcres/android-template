package com.davidcrespo.template.core.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProgressButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = CircleShape,
    progressDurationMillis: Int = 5000,
    onClick: () -> Unit,
    onProgressComplete: () -> Unit = {}
) {
    val animatedWithFraction = remember { Animatable(0f) }
    val density = LocalDensity.current

    LaunchedEffect(Unit) {
        animatedWithFraction.animateTo(
            targetValue = 1f, // Animate to 100%
            animationSpec = tween(durationMillis = progressDurationMillis, easing = LinearEasing)
        )
        onProgressComplete()
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .height(IntrinsicSize.Max)
            .drawBehind {
                val defaultButtonClipShape = shape
                val outline = defaultButtonClipShape.createOutline(
                    this.size,
                    layoutDirection,
                    density
                )
                val buttonShapePath = Path().apply { addOutline(outline) }

                clipPath(buttonShapePath) {
                    drawRect(
                        color = color,
                        size = Size(
                            width = this.size.width * animatedWithFraction.value,
                            height = this.size.height
                        ),
                    )
                }
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = shape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun ProgressButtonPreview() {
    ProgressButton(
        text = "Progress Button",
        color = Color.Green,
        onClick = {},
        onProgressComplete = {}
    )
}
