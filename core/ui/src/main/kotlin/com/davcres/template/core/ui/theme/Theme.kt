package com.davcres.template.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.davcres.template.core.common.models.ThemeMode

private val LightColorScheme = lightColorScheme(
    primary = BrandColors.Primary,
    onPrimary = BrandColors.OnPrimary,
    primaryContainer = BrandColors.PrimaryContainer,
    onPrimaryContainer = BrandColors.OnPrimaryContainer,

    secondary = BrandColors.Secondary,
    onSecondary = BrandColors.OnSecondary,
    secondaryContainer = BrandColors.SecondaryContainer,
    onSecondaryContainer = BrandColors.OnSecondaryContainer,

    tertiary = BrandColors.Tertiary,
    onTertiary = BrandColors.OnTertiary,
    tertiaryContainer = BrandColors.TertiaryContainer,
    onTertiaryContainer = BrandColors.OnTertiaryContainer,

    error = BrandColors.Error,
    onError = BrandColors.OnError,
    errorContainer = BrandColors.ErrorContainer,
    onErrorContainer = BrandColors.OnErrorContainer,

    background = SurfaceColors.BackgroundLight,
    onBackground = SurfaceColors.OnBackgroundLight,
    surface = SurfaceColors.SurfaceLight,
    onSurface = SurfaceColors.OnSurfaceLight,

    outline = BrandColors.NeutralVariant
)

/**
 * Paleta oscura correspondiente.
 * Si quieres otro contraste, ajusta estos valores a mano
 * en lugar de sólo reciclar los de BrandColors.
 */
private val DarkColorScheme = darkColorScheme(
    primary = BrandColors.Primary,
    onPrimary = BrandColors.OnPrimary,
    primaryContainer = BrandColors.PrimaryContainer,
    onPrimaryContainer = BrandColors.OnPrimaryContainer,

    secondary = BrandColors.Secondary,
    onSecondary = BrandColors.OnSecondary,
    secondaryContainer = BrandColors.SecondaryContainer,
    onSecondaryContainer = BrandColors.OnSecondaryContainer,

    tertiary = BrandColors.Tertiary,
    onTertiary = BrandColors.OnTertiary,
    tertiaryContainer = BrandColors.TertiaryContainer,
    onTertiaryContainer = BrandColors.OnTertiaryContainer,

    error = BrandColors.Error,
    onError = BrandColors.OnError,
    errorContainer = BrandColors.ErrorContainer,
    onErrorContainer = BrandColors.OnErrorContainer,

    background = SurfaceColors.BackgroundDark,
    onBackground = SurfaceColors.OnBackgroundDark,
    surface = SurfaceColors.SurfaceDark,
    onSurface = SurfaceColors.OnSurfaceDark,

    outline = BrandColors.NeutralVariant
)

@Composable
fun TemplateTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
