package com.davcres.template.auth.presentation.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.davcres.template.core.navigation.BottomNavKey

enum class BottomDestinations(
    val route: NavKey,
    @StringRes val idName: Int,
    val icon: ImageVector
) {
    HOME(BottomNavKey.Home, R.string.bottom_nav_home, Icons.Default.Home),
    SWIPE(BottomNavKey.Swipe, R.string.bottom_nav_swipe, Icons.Default.Star),
    SETTINGS(BottomNavKey.Settings, R.string.bottom_nav_settings, Icons.Default.Settings);

    companion object {
        fun fromRoute(route: NavKey?): BottomDestinations =
            entries.firstOrNull { it.route == route } ?: HOME
    }
}
