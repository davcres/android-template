package com.davidcrespo.template.presentation.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.davidcrespo.template.presentation.navigation.destinations.Home
import com.davidcrespo.template.presentation.navigation.destinations.Theme
import com.davidcrespo.template.presentation.ui.HomeScreen
import com.davidcrespo.template.presentation.ui.ThemeScreen

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
