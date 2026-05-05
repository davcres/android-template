package com.davcres.template.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface RootKey : NavKey {
    @Serializable data object Login : RootKey

    @Serializable data object SignUp : RootKey

    @Serializable data object Main : RootKey
}

sealed interface BottomNavKey : NavKey {
    @Serializable data object Home : BottomNavKey

    @Serializable data object Swipe : BottomNavKey

    @Serializable data object Settings : BottomNavKey
}
