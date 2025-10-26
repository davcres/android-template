package com.davidcrespo.template.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Shadow(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Color.Gray
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val shape = RoundedCornerShape(50.dp)

        Box(
            modifier = Modifier
                .dropShadow(shape = shape) {
                    radius = 60f
                    // color = Color.Red
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Green, Color.Cyan)
                    )
                }
                .border(
                    width = 1.dp,
                    shape = shape,
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Yellow, Color.Cyan)
                    ),
                )
                .size(100.dp)
                .background(
                    color = Color.Black,
                    shape = shape,
                )
                .innerShadow(shape = shape) {
                    radius = 90f
                    // color = Color.Red
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Green, Color.Cyan)
                    )
                    alpha = .4f
                }
        )

        Box(
            modifier = Modifier
                .dropShadow(shape = shape) {
                    radius = 50f
                    brush = Brush.verticalGradient(
                        colors = listOf(White, White, Color.Black.copy(alpha = .2f))
                    )
                }
                .border(
                    width = 2.dp,
                    shape = shape,
                    brush = Brush.verticalGradient(
                        colors = listOf(White, Color.Black.copy(alpha = .3f))
                    ),
                )
                .size(100.dp)
                .background(
                    color = Color.Gray,
                    shape = shape,
                )
                .innerShadow(shape = shape) {
                    radius = 90f
                    brush = Brush.verticalGradient(
                        colors = listOf(White, Color.Black.copy(alpha = .2f))
                    )
                }
        )
    }
}

@Preview
@Composable
private fun ShadowPreview() {
    Shadow()
}
