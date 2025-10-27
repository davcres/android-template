package com.davcres.template.core.ui.extensions.modifiers

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.drawUnderline(color: Color): Modifier {
    return this.padding(bottom = 2.dp) then Modifier.drawBehind {
        val lineThickness = 1.dp.toPx()
        val yOffset = size.height + lineThickness
        drawLine(
            color = color,
            start = Offset(0f, yOffset),
            end = Offset(size.width, yOffset),
            strokeWidth = lineThickness
        )
    }
}
