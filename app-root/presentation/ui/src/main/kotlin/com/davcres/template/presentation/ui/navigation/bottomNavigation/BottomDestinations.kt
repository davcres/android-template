package com.davcres.template.presentation.ui.navigation.bottomNavigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Home
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Settings
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Swipe
import com.davcres.template.shared.presentation.ui.R

enum class BottomDestinations(
    val route: NavKey,
    @StringRes val idName: Int,
    val icon: ImageVector
) {
    HOME(Home, R.string.bottom_nav_home, Icons.Default.Home),
    SWIPE(Swipe, R.string.bottom_nav_swipe, Icons.Default.Star),
    SETTINGS(Settings, R.string.bottom_nav_settings, Icons.Default.Settings);

    companion object {
        fun fromRoute(route: NavKey?): BottomDestinations =
            entries.firstOrNull { it.route == route } ?: HOME
    }
}
