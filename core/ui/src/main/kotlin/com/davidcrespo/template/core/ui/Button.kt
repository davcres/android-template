package com.davidcrespo.template.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidcrespo.template.core.common.Constants.UI.UITest.ID
import com.davidcrespo.template.core.common.extensions.toCamelCase
import com.davidcrespo.template.core.ui.auxiliary.ButtonStyle
import com.davidcrespo.template.core.ui.extensions.bounceClick
import com.davidcrespo.template.core.ui.extensions.drawUnderline
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonPrimary
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonPrimaryDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonPrimaryDisabledText
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonSecondary
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonSecondaryDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTertiary
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTertiaryDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTogglePrimarySelected
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTogglePrimarySelectedDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTogglePrimarySelectedDisabledText
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTogglePrimaryUnselected
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonTogglePrimaryUnselectedDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonToggleSecondarySelected
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonToggleSecondarySelectedDisabled
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonToggleSecondarySelectedDisabledText
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonToggleSecondaryUnselected
import com.davidcrespo.template.core.ui.theme.Color.Button.ButtonToggleSecondaryUnselectedDisabled
import com.davidcrespo.template.core.ui.theme.Color.Common.white

@Suppress("CyclomaticComplexMethod")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Button(
    text: String? = null,
    onClick: () -> Unit,
    buttonStyle: ButtonStyle,
    fontSize: TextUnit = 16.sp,
    buttonModifier: Modifier = Modifier,
    underline: Boolean = false,
    contentModifier: Modifier = Modifier,
    enabled: Boolean = true,
    isSelected: Boolean = false,
    @DrawableRes leftDrawable: Int? = null,
    @DrawableRes rightDrawable: Int? = null,
    leftImageVector: ImageVector? = null,
    rightImageVector: ImageVector? = null,
    paddingBetweenIconAndText: Dp = 8.dp,
    contentDescription: String? = null,
    label: String? = null,
    shape: Shape = ButtonDefaults.shape,
    shadowElevation: Dp? = null,
    tonalElevation: Dp? = null,
    iconSize: Dp? = null,
    tint: Color = Color.Unspecified,
    tag: String? = null,
    showSurface: Boolean = true,
    buttonCustomColor: Color? = null,
    contentCustomColor: Color? = null,
    borderCustomColor: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val (containerColor, contentColor, buttonBorder) = when (buttonStyle) {
        ButtonStyle.PRIMARY -> {
            Triple(
                first = when {
                    !enabled -> ButtonPrimaryDisabled
                    else -> ButtonPrimary
                },
                second = when {
                    !enabled -> ButtonPrimaryDisabledText
                    else -> white
                },
                third = null
            )
        }
        ButtonStyle.SECONDARY -> {
            Triple(
                first = white,
                second = when {
                    !enabled -> ButtonSecondaryDisabled
                    else -> ButtonSecondary
                },
                third = when {
                    !enabled -> BorderStroke(1.dp, ButtonSecondaryDisabled)
                    else -> BorderStroke(1.dp, ButtonSecondary)
                }
            )
        }
        ButtonStyle.TERTIARY -> {
            Triple(
                first = Color.Transparent,
                second = when {
                    !enabled -> ButtonTertiaryDisabled
                    else -> ButtonTertiary
                },
                third = null
            )
        }
        ButtonStyle.TOGGLE_PRIMARY -> {
            Triple(
                first = if (isSelected) {
                    when {
                        !enabled -> ButtonTogglePrimarySelectedDisabled
                        else -> ButtonTogglePrimarySelected
                    }
                } else {
                    white
                },
                second = if (isSelected) {
                    when {
                        !enabled -> ButtonTogglePrimarySelectedDisabledText
                        else -> white
                    }
                } else {
                    when {
                        !enabled -> ButtonTogglePrimaryUnselectedDisabled
                        else -> ButtonTogglePrimaryUnselected
                    }
                },
                third = if (isSelected) {
                    null
                } else {
                    when {
                        !enabled -> BorderStroke(1.dp, ButtonTogglePrimaryUnselectedDisabled)
                        else -> BorderStroke(1.dp, ButtonTogglePrimaryUnselected)
                    }
                }
            )
        }
        ButtonStyle.TOGGLE_SECONDARY -> {
            Triple(
                first = if (isSelected) {
                    when {
                        !enabled -> ButtonToggleSecondarySelectedDisabled
                        else -> ButtonToggleSecondarySelected
                    }
                } else {
                    white
                },
                second = if (isSelected) {
                    when {
                        !enabled -> ButtonToggleSecondarySelectedDisabledText
                        else -> white
                    }
                } else {
                    when {
                        !enabled -> ButtonToggleSecondaryUnselectedDisabled
                        else -> ButtonToggleSecondaryUnselected
                    }
                },
                third = if (isSelected) {
                    null
                } else {
                    when {
                        !enabled -> BorderStroke(1.dp, ButtonToggleSecondaryUnselectedDisabled)
                        else -> BorderStroke(1.dp, ButtonToggleSecondaryUnselected)
                    }
                }
            )
        }
    }

    val shapeUsed = if (buttonStyle == ButtonStyle.TERTIARY) RoundedCornerShape(4.dp) else shape

    val iconModifier = Modifier
        .then(Modifier.size(iconSize ?: 16.dp))

    val content = @Composable {
        CompositionLocalProvider(LocalContentColor provides (contentCustomColor ?: contentColor)) {
            ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                Row(
                    modifier = Modifier.padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = contentModifier,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when {
                            leftDrawable != null -> Image(
                                painter = painterResource(leftDrawable),
                                contentDescription = contentDescription,
                                modifier = iconModifier,
                                colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
                            )
                            leftImageVector != null -> Icon(
                                imageVector = leftImageVector,
                                contentDescription = contentDescription,
                                tint = tint,
                                modifier = iconModifier
                            )
                        }

                        if ((leftDrawable != null || leftImageVector != null) && !text.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.size(paddingBetweenIconAndText))
                        }

                        if (!text.isNullOrEmpty()) {
                            Text(
                                text = text,
                                fontSize = fontSize,
                                color = contentCustomColor ?: contentColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .then(
                                        if (underline) Modifier.drawUnderline(
                                            contentCustomColor ?: contentColor
                                        ) else Modifier
                                    )
                            )
                        }

                        if ((rightDrawable != null || rightImageVector != null) && !text.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.size(paddingBetweenIconAndText))
                        }

                        when {
                            rightDrawable != null -> Image(
                                painter = painterResource(rightDrawable),
                                contentDescription = contentDescription,
                                modifier = iconModifier,
                                colorFilter = if (tint != Color.Unspecified) ColorFilter.tint(tint) else null
                            )
                            rightImageVector != null -> Icon(
                                imageVector = rightImageVector,
                                contentDescription = contentDescription,
                                tint = tint,
                                modifier = iconModifier
                            )
                        }
                    }
                }
            }
        }
    }

    val baseModifier = buttonModifier
        .semantics {
            role = if (buttonStyle == ButtonStyle.TOGGLE_PRIMARY || buttonStyle == ButtonStyle.TOGGLE_SECONDARY) Role.Switch else Role.Button
            tag?.let { testTag = "$ID $it".toCamelCase() }
            testTagsAsResourceId = true
            contentDescription?.let { this.contentDescription = it }
            label?.let {
                onClick(label = it) {
                    onClick()
                    true
                }
            }
        }
        .bounceClick()

    if (showSurface) {
        Surface(
            onClick = {
                onClick()
            },
            modifier = baseModifier,
            enabled = enabled,
            shape = shapeUsed,
            color = buttonCustomColor ?: containerColor,
            contentColor = contentCustomColor ?: contentColor,
            tonalElevation = tonalElevation ?: 0.dp,
            shadowElevation = shadowElevation ?: 0.dp,
            border = borderCustomColor ?: buttonBorder,
            interactionSource = interactionSource
        ) {
            content()
        }
    } else {
        Box(
            modifier = baseModifier
                .then(
                    if (shadowElevation != null) {
                        Modifier.shadow(
                            shadowElevation,
                            shapeUsed
                        )
                    } else {
                        Modifier
                    }
                )
                .clip(shapeUsed)
                .background(buttonCustomColor ?: containerColor, shapeUsed)
                .border(
                    borderCustomColor ?: buttonBorder ?: BorderStroke(0.dp, Color.Transparent),
                    shapeUsed
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled
                ) {
                    onClick()
                }
        ) {
            content()
        }
    }
}

data class ButtonPreviewParams(
    val buttonStyle: ButtonStyle,
    val showIcon: Boolean,
    val enabled: Boolean
)

class ButtonPreviewParamsProvider : PreviewParameterProvider<ButtonPreviewParams> {
    override val values = getAllCombinations()

    private fun getAllCombinations(): Sequence<ButtonPreviewParams> {
        val samples = mutableListOf<ButtonPreviewParams>()

        ButtonStyle.entries.forEach { buttonStyle ->
            listOf(true, false).forEach { showIcon ->
                listOf(true, false).forEach { enabled ->
                    samples.add(
                        ButtonPreviewParams(
                            buttonStyle = buttonStyle,
                            showIcon = showIcon,
                            enabled = enabled
                        )
                    )
                }
            }
        }
        return samples.asSequence()
    }
}

@Preview
@Composable
fun ButtonPreview(
    @PreviewParameter(ButtonPreviewParamsProvider::class) buttonParams: ButtonPreviewParams
) {
    Button(
        text = "Exit",
        fontSize = 16.sp,
        onClick = {},
        buttonStyle = buttonParams.buttonStyle,
        underline = false,
        enabled = buttonParams.enabled,
        showSurface = true
    )
}
