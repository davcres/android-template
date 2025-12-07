package com.davcres.template.presentation.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.davcres.template.presentation.ui.HomeScreen
import com.davcres.template.presentation.ui.LoginScreen
import com.davcres.template.presentation.ui.MainScreen
import com.davcres.template.presentation.ui.SignUpScreen
import com.davcres.template.presentation.ui.SwipeScreen
import com.davcres.template.presentation.ui.ThemeScreen
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Home
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Settings
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Swipe
import com.davcres.template.presentation.ui.navigation.destinations.RootKey.Login
import com.davcres.template.presentation.ui.navigation.destinations.RootKey.Main
import com.davcres.template.presentation.ui.navigation.destinations.RootKey.SignUp

fun EntryProviderScope<NavKey>.mainNavigation(backStack: NavBackStack<NavKey>) {
    entry<Login> {
        LoginScreen(
            onLogin = {
                backStack.clear()
                backStack.add(Main)
            },
            onSignUp = {
                backStack.add(SignUp)
            }
        )
    }

    entry<SignUp> {
        SignUpScreen(
            onSignUp = {
                backStack.clear()
                backStack.add(Main)
            },
            onBack = {
                if (backStack.size > 1) backStack.removeLastOrNull()
            }
        )
    }

    entry<Main> {
        MainScreen(
            logout = {
                backStack.clear()
                backStack.add(Login)
            }
        )
    }
}

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
