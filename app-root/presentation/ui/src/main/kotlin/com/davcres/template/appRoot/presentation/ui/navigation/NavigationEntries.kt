package com.davcres.template.appRoot.presentation.ui.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.davcres.template.auth.presentation.ui.LoginScreen
import com.davcres.template.auth.presentation.ui.MainScreen
import com.davcres.template.auth.presentation.ui.SignUpScreen
import com.davcres.template.core.navigation.RootKey.Login
import com.davcres.template.core.navigation.RootKey.Main
import com.davcres.template.core.navigation.RootKey.SignUp

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
