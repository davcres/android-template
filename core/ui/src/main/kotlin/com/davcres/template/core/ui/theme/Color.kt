package com.davcres.template.core.ui.theme

import androidx.compose.ui.graphics.Color

object Color {
    object Common {
        val white = Color(0xFFFFFFFF)
    }

    object Backgrounds {
        val whiteBackground = Color(0xFFFFFFFF)
    }

    object Button {
        val ButtonPrimary = Color(0xFF98A92A)
        val ButtonPrimaryDisabled = Color(0xFFE7E6E6)
        val ButtonPrimaryDisabledText = Color(0xFF7A7578)

        val ButtonSecondary = Color(0xFF095751)
        val ButtonSecondaryDisabled = Color(0xFF7A7578)

        val ButtonTertiary = Color(0xFF095751)
        val ButtonTertiaryDisabled = Color(0xFF7A7578)

        val ButtonTogglePrimarySelected = Color(0xFF98A92A)
        val ButtonTogglePrimarySelectedDisabled = Color(0xFFE7E6E6)
        val ButtonTogglePrimarySelectedDisabledText = Color(0xFF7A7578)
        val ButtonTogglePrimaryUnselected = Color(0xFF98A92A)
        val ButtonTogglePrimaryUnselectedDisabled = Color(0xFF7A7578)

        val ButtonToggleSecondarySelected = Color(0xFF095751)
        val ButtonToggleSecondarySelectedDisabled = Color(0xFFE7E6E6)
        val ButtonToggleSecondarySelectedDisabledText = Color(0xFF7A7578)
        val ButtonToggleSecondaryUnselected = Color(0xFF095751)
        val ButtonToggleSecondaryUnselectedDisabled = Color(0xFF7A7578)
    }
}

/**
 * Colores base de marca pensados para negocios tipo cafetería.
 */
object BrandColors {
    // Café tostado como color principal
    val Primary = Color(0xFF795548) // brown 500
    val OnPrimary = Color(0xFFFFFFFF)

    // Versión más clara para chips, botones elevados, etc.
    val PrimaryContainer = Color(0xFFD7CCC8) // brown 200
    val OnPrimaryContainer = Color(0xFF3E2723) // brown 900

    // Secundario: un verde suave tipo “matcha / planta” para detalles
    val Secondary = Color(0xFF8BC34A) // light green 500
    val OnSecondary = Color(0xFF1B2100)
    val SecondaryContainer = Color(0xFFDCEDC8) // light green 200
    val OnSecondaryContainer = Color(0xFF1B2100)

    // Terciario: un tono “caramelo” para acentos puntuales
    val Tertiary = Color(0xFFFFA726) // orange 400
    val OnTertiary = Color(0xFF3A1F00)
    val TertiaryContainer = Color(0xFFFFE0B2) // orange 200
    val OnTertiaryContainer = Color(0xFF3A1F00)

    // Errores
    val Error = Color(0xFFB3261E)
    val OnError = Color(0xFFFFFFFF)
    val ErrorContainer = Color(0xFFF9DEDC)
    val OnErrorContainer = Color(0xFF410E0B)

    // Neutrales
    val Neutral = Color(0xFF5D4037) // marrón neutro
    val NeutralVariant = Color(0xFF8D6E63)
}

/**
 * Colores de fondo/superficie cálidos tipo “papel/factura de cafetería”.
 */
object SurfaceColors {
    // Fondo claro tipo crema
    val BackgroundLight = Color(0xFFFFF8E1) // amber 50
    val SurfaceLight = Color(0xFFFFFBFE)
    val OnBackgroundLight = Color(0xFF3E2723)
    val OnSurfaceLight = Color(0xFF3E2723)

    // Modo oscuro: fondo café muy oscuro
    val BackgroundDark = Color(0xFF12100F)
    val SurfaceDark = Color(0xFF1C1B1F)
    val OnBackgroundDark = Color(0xFFEDE0D4)
    val OnSurfaceDark = Color(0xFFE6E1E5)
}
