/*
 * Copyright 2025 Kyriakos Georgiopoulos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.davcres.template.auth.presentation.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import java.util.Random
import kotlin.math.abs
import kotlin.math.sign

/**
 * Immutable, per-card placement used to create a natural "messy deck" layout.
 */
private data class Placement(
    val dx: Dp,
    val baseY: Dp,
    val rot: Float,
    val scale: Float,
    val elevation: Dp
)

private const val VISIBLE_STACK = 5
private val STACK_STEP_Y = 8.dp
private const val CARD_HEIGHT_FRACTION = 0.55f

/**
 * Static list of drawable resource IDs for Android figures.
 */
private val AndroidFigures: List<Int> = listOf(
    R.drawable.red_circle,
    R.drawable.blue_circle
)

/**
 * Root screen hosting a playful background and a swipeable deck of Android figures.
 *
 * @param modifier Optional [Modifier] for the screen container.
 * @param figures List of drawable resource IDs to display.
 * @param onBack Callback invoked when the user has swiped all cards away.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeScreen(
    modifier: Modifier = Modifier,
    figures: List<Int> = AndroidFigures,
    onBack: () -> Unit = {}
) {
    var wallpaperShift by rememberSaveable { mutableFloatStateOf(0f) }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0D10))
    ) {
        AndroidLogosBackground(parallax = wallpaperShift)
        PlayfulHeader()
        Scaffold(
            containerColor = Color.Transparent,
            modifier = modifier
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                FigureDeck(
                    figures = figures,
                    onFinished = onBack,
                    onDragProgress = { wallpaperShift = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 160.dp)
                        .zIndex(1f)
                )
            }
        }
    }
}

@Composable
/**
 * Background with large, faint Android logos that subtly parallax in response to dragging.
 *
 * @param parallax Normalized horizontal shift applied to the background elements.
 */
private fun AndroidLogosBackground(parallax: Float) {
    val bg = Color(0xFF0B0D10)
    val tint = Color(0xFF3DDC84)
    val density = LocalDensity.current

    val spots = listOf(
        Spot(0.24f, 0.18f, -10f, 0.42f, 0.04f),
        Spot(0.76f, 0.20f, 8f, 0.40f, 0.05f),
        Spot(0.65f, 0.35f, -2f, 0.40f, 0.05f),
        Spot(0.25f, 0.55f, 10f, 0.40f, 0.05f),
        Spot(0.75f, 0.65f, -12f, 0.38f, 0.05f),
        Spot(0.26f, 0.80f, -5f, 0.40f, 0.04f),
        Spot(0.74f, 0.90f, 10f, 0.38f, 0.05f)
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val widthPx = constraints.maxWidth.toFloat()
            val heightPx = constraints.maxHeight.toFloat()
            val base = minOf(widthPx, heightPx)
            val shift = (widthPx * 0.012f) * parallax.coerceIn(-1f, 1f)

            spots.forEach { s ->
                val logoSizePx = base * s.sizeFactor
                val logoSizeDp = with(density) { logoSizePx.toDp() }
                val cx = widthPx * s.nx + shift
                val cy = heightPx * s.ny

                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                (cx - logoSizePx / 2f).toInt(),
                                (cy - logoSizePx / 2f).toInt()
                            )
                        }
                        .size(logoSizeDp)
                        .graphicsLayer {
                            rotationZ = s.rot
                            alpha = s.alpha
                        }
                )
            }
        }
    }
}

@Immutable
private data class Spot(
    val nx: Float,
    val ny: Float,
    val rot: Float,
    val sizeFactor: Float,
    val alpha: Float
)

@Composable
/**
 * Generates one-time random yet stable placements for cards in the deck.
 *
 * @param count Total number of cards.
 * @return List of [Placement] entries corresponding to each card index.
 */
private fun rememberPlacements(count: Int): List<Placement> = remember(count) {
    val rnd = Random(2025L)
    fun rf(min: Float, max: Float) = (min + rnd.nextFloat() * (max - min))
    List(count) { i ->
        val depth = i
        val baseStep = STACK_STEP_Y * depth
        val dx = rf(-10f, 10f).dp
        val dy = rf(-6f, 6f).dp
        val rot = rf(-3.2f, 3.2f)
        val scaleBase = (1f - 0.025f * depth).coerceAtLeast(0.86f)
        val scaleJitter = rf(-0.02f, 0f)
        val scale = (scaleBase + scaleJitter).coerceAtMost(1f)
        val elevation = (28 - depth * 4 + rf(-2f, 2f)).toInt().coerceIn(6, 28).dp
        Placement(dx, baseStep + dy, rot, scale, elevation)
    }
}

@Composable
/**
 * A visible stack of swipeable and static cards. The top card is interactive.
 *
 * @param figures Drawable resource IDs for each card.
 * @param onFinished Invoked when all cards have been dismissed.
 * @param onDragProgress Callback with a normalized horizontal drag value for parallax.
 * @param modifier Optional [Modifier].
 */
private fun FigureDeck(
    figures: List<Int>,
    onFinished: () -> Unit,
    onDragProgress: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var topIndex by rememberSaveable { mutableStateOf(0) }
    val haptics = LocalHapticFeedback.current
    val placements = rememberPlacements(figures.size)
    val lastIndex = figures.lastIndex

    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (topIndex > lastIndex) {
            LaunchedEffect(Unit) { onFinished() }
            return@Box
        }
        val end = (topIndex + VISIBLE_STACK - 1).coerceAtMost(lastIndex)
        for (index in end downTo topIndex) {
            val p = placements[index]
            if (index == topIndex) {
                key(index) {
                    SwipeableCard(
                        cardId = index,
                        onDragProgress = onDragProgress,
                        imageResId = figures[index],
                        baseScale = 1f,
                        baseTranslateY = 0.dp,
                        elevation = p.elevation,
                        enterFromScale = p.scale,
                        enterFromTranslateY = p.baseY,
                        baseDx = p.dx
                    ) { _ ->
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        val next = topIndex + 1
                        topIndex = next.coerceAtMost(figures.size)
                        if (next > lastIndex) onFinished()
                    }
                }
            } else {
                StaticCard(
                    imageResId = figures[index],
                    scale = p.scale,
                    translateY = p.baseY,
                    elevation = p.elevation,
                    alpha = if (index == topIndex + 1) 1f else 0.92f,
                    jitterDx = p.dx,
                    jitterRot = p.rot,
                )
            }
        }
    }
}

@Composable
/**
 * Non-interactive card in the stack with jittered placement and rotation.
 */
private fun StaticCard(
    @DrawableRes imageResId: Int,
    scale: Float,
    translateY: Dp,
    elevation: Dp,
    alpha: Float,
    jitterDx: Dp,
    jitterRot: Float
) {
    ImageCard(
        imageResId = imageResId,
        elevation = elevation,
        cardKey = imageResId,
        modifier = Modifier
            .offset(x = jitterDx, y = translateY)
            .graphicsLayer {
                this.alpha = alpha
                scaleX = scale
                scaleY = scale
                rotationZ = jitterRot
            }
            .zIndex(0f)
    )
}

@Composable
/**
 * Header text displayed above the deck.
 *
 * @param title Header title.
 * @param subtitle Header subtitle.
 */
private fun PlayfulHeader(
    title: String = "Meet the Andro-Crew",
    subtitle: String = "Swipe to shuffle the droids →"
) {
    Box(
        Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .drawBehind {
                drawRect(
                    Brush.verticalGradient(
                        0f to Color.Black.copy(alpha = 0.18f),
                        1f to Color.Transparent
                    )
                )
            }
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Column {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                )
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = Color.White.copy(alpha = 0.9f),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
/**
 * Interactive top card supporting drag, fling, and dismissal animations.
 *
 * @param cardId Stable ID distinguishing animation states across recompositions.
 * @param imageResId Drawable resource to render.
 * @param baseScale Target scale after enter animation.
 * @param baseTranslateY Target Y translation after enter animation.
 * @param elevation Visual elevation used for shadow rendering.
 * @param enterFromScale Initial scale for the enter animation.
 * @param enterFromTranslateY Initial Y offset for the enter animation.
 * @param baseDx Static horizontal offset to stagger the stack.
 * @param onDragProgress Callback with a normalized horizontal drag value.
 * @param onDismiss Invoked with direction (-1 left, 1 right) when card is dismissed.
 */
private fun SwipeableCard(
    cardId: Int,
    @DrawableRes imageResId: Int,
    baseScale: Float,
    baseTranslateY: Dp,
    elevation: Dp,
    enterFromScale: Float,
    enterFromTranslateY: Dp,
    baseDx: Dp,
    onDragProgress: (Float) -> Unit,
    onDismiss: (direction: Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val offsetX = remember(cardId) { Animatable(0f) }
    val offsetY = remember(cardId) { Animatable(0f) }
    val rotationZ = remember(cardId) { Animatable(0f) }

    val scaleAnim = remember(cardId) { Animatable(enterFromScale) }
    val yAnim = remember(cardId) { Animatable(enterFromTranslateY.value) }
    val liftAnim = remember(cardId) { Animatable(0f) }

    LaunchedEffect(cardId, baseScale, baseTranslateY) {
        scaleAnim.snapTo(enterFromScale)
        yAnim.snapTo(enterFromTranslateY.value)
        liftAnim.snapTo(0f)
        scaleAnim.animateTo(baseScale, spring(stiffness = 260f, dampingRatio = 0.86f))
        yAnim.animateTo(baseTranslateY.value, spring(stiffness = 280f, dampingRatio = 0.9f))
        liftAnim.animateTo(1f, spring(stiffness = 220f, dampingRatio = 0.92f))
    }

    BoxWithConstraints(Modifier.fillMaxSize()) {
        val widthPx = constraints.maxWidth.toFloat()
        var lastVX = 0f

        fun settleOrDismiss() {
            val threshold = widthPx * 0.22f
            val x = offsetX.value
            val dir = sign(x).toInt().coerceIn(-1, 1)
            val strongFling = abs(lastVX) > 2200f
            if (abs(x) > threshold || strongFling) {
                scope.launch {
                    offsetX.animateTo(dir * widthPx * 1.25f, tween(220))
                    onDragProgress(0f)
                    onDismiss(dir)
                }
            } else {
                scope.launch {
                    offsetX.animateTo(
                        0f,
                        spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.9f)
                    )
                    offsetY.animateTo(
                        0f,
                        spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.9f)
                    )
                    rotationZ.animateTo(0f, tween(220))
                    onDragProgress(0f)
                }
            }
        }

        val drag: suspend (dx: Float, dy: Float, vx: Float, vy: Float) -> Unit = { dx, dy, vx, _ ->
            offsetX.snapTo(offsetX.value + dx)
            offsetY.snapTo(offsetY.value + dy / 3f)
            rotationZ.snapTo((offsetX.value / 44f).coerceIn(-10f, 10f))
            lastVX = vx
            onDragProgress((offsetX.value / (widthPx * 0.65f)).coerceIn(-1f, 1f))
        }

        val dragScale by remember(cardId) {
            derivedStateOf {
                val frac = (abs(offsetX.value) / (widthPx * 0.6f)).coerceIn(0f, 1f)
                1f - 0.02f * frac
            }
        }
        val downBias by remember(cardId) {
            derivedStateOf {
                val frac = (abs(offsetX.value) / widthPx).coerceIn(0f, 1f)
                8f * frac
            }
        }

        val liftedElevation = elevation * (0.85f + 0.15f * liftAnim.value)

        ImageCard(
            imageResId = imageResId,
            elevation = liftedElevation,
            cardKey = cardId,
            modifier = Modifier
                .offset(x = baseDx, y = Dp(yAnim.value))
                .graphicsLayer {
                    scaleX = scaleAnim.value * dragScale
                    scaleY = scaleAnim.value * dragScale
                    translationX = offsetX.value
                    translationY = offsetY.value + downBias
                    this.rotationZ = rotationZ.value
                    cameraDistance = 36_000f
                    transformOrigin = TransformOrigin(0.5f, 0.85f)
                }
                .pointerInput(cardId) {
                    var tracker = VelocityTracker()

                    detectDragGestures(
                        onDragStart = {
                            tracker = VelocityTracker()
                        },
                        onDragCancel = {
                            settleOrDismiss()
                        },
                        onDragEnd = {
                            val v = tracker.calculateVelocity()
                            lastVX = v.x
                            settleOrDismiss()
                        }
                    ) { change, dragAmount ->
                        tracker.addPosition(change.uptimeMillis, change.position)
                        change.consume()

                        scope.launch {
                            drag(dragAmount.x, dragAmount.y, 0f, 0f)
                        }
                    }
                }
                .zIndex(1f)
        )
    }
}

@Composable
/**
 * Card container with styling, background, soft shadow and the provided image.
 *
 * @param imageResId Drawable resource to render inside the card.
 * @param elevation Visual elevation used for shadow rendering.
 * @param cardKey Stable key used to derive a consistent wash color.
 * @param modifier Optional [Modifier].
 */
private fun ImageCard(
    @DrawableRes imageResId: Int,
    elevation: Dp,
    cardKey: Int,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(28.dp)

    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(CARD_HEIGHT_FRACTION)
            .shadow(
                elevation = elevation / 6,
                shape = shape,
                ambientColor = Color(0x22182434),
                spotColor = Color(0x22182434)
            )
            .softCardShadow(alpha = 0.16f, spread = 0.9f, lift = 0.32f)
            .clip(shape)
            .background(Color(0xFFFBFCFE))
            .border(1.dp, Color(0x14000000), shape)
            .drawBehind {
                drawRoundRect(
                    color = Color.White.copy(alpha = 0.45f),
                    topLeft = Offset(1f, 1f),
                    size = Size(size.width - 2f, size.height - 2f),
                    cornerRadius = CornerRadius(28.dp.toPx(), 28.dp.toPx()),
                    style = Stroke(width = 1f)
                )
                drawRect(
                    Brush.verticalGradient(
                        0f to Color.White.copy(alpha = 0.25f),
                        0.3f to Color.White.copy(alpha = 0.10f),
                        1f to Color.Transparent
                    )
                )
            }
    ) {
        /*Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )*/
        Icon(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
                .height(4.dp)
                .fillMaxWidth(0.14f)
                .clip(RoundedCornerShape(50))
                .background(Color(0x1A000000))
        )
    }
}

/**
 * Adds a soft, radial shadow beneath a card for depth.
 */
private fun Modifier.softCardShadow(
    alpha: Float = 0.18f,
    spread: Float = 0.86f,
    lift: Float = 0.35f
) = drawBehind {
    val w = size.width * spread
    val h = size.height * 0.22f
    val left = (size.width - w) / 2f
    val top = size.height - h * (1f + lift)
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color.Black.copy(alpha = alpha), Color.Transparent),
            center = Offset(size.width / 2f, size.height - h * lift),
            radius = w
        ),
        topLeft = Offset(left, top),
        size = Size(w, h)
    )
}
