package com.davcres.template.presentation.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.davcres.template.presentation.navigation.destinations.Home
import com.davcres.template.presentation.navigation.destinations.SwipeScreen
import com.davcres.template.presentation.navigation.destinations.Theme
import com.davcres.template.presentation.ui.HomeScreen
import com.davcres.template.presentation.ui.SwipeScreen
import com.davcres.template.presentation.ui.ThemeScreen

fun EntryProviderBuilder<NavKey>.homeNavigation(backStack: NavBackStack<NavKey>) {
    entry<Home> {
        HomeScreen(
            onClick = {
                backStack.add(Theme)
            },
        )
    }
}

fun EntryProviderBuilder<NavKey>.settingsNavigation(backStack: NavBackStack<NavKey>) {
    entry<Theme> {
        ThemeScreen(
            onBack = {
                backStack.removeLastOrNull()
            }
        )
    }
}

fun EntryProviderBuilder<NavKey>.swapsNavigation(backStack: NavBackStack<NavKey>) {
    entry<SwipeScreen> {
        SwipeScreen(
            onBack = {
                if (backStack.size > 1) backStack.removeLastOrNull()
            }
        )
    }
}
