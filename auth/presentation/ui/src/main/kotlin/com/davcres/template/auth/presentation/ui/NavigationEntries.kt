package com.davcres.template.auth.presentation.ui

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.davcres.template.core.navigation.BottomNavKey.Home
import com.davcres.template.core.navigation.BottomNavKey.Settings
import com.davcres.template.core.navigation.BottomNavKey.Swipe

fun EntryProviderScope<NavKey>.bottomNavigation(backStack: NavBackStack<NavKey>) {
    entry<Home> {
        HomeScreen(
            onClick = {
                backStack.add(Settings)
            },
        )
    }

    entry<Settings> {
        ThemeScreen(
            onBack = {
                if (backStack.size > 1) backStack.removeLastOrNull()
            }
        )
    }

    entry<Swipe> {
        SwipeScreen(
            onBack = {
                if (backStack.size > 1) backStack.removeLastOrNull()
            }
        )
    }
}
