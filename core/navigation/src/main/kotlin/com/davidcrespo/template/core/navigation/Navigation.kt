package com.davidcrespo.template.core.navigation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.davidcrespo.template.presentation.navigation.destinations.Home
import com.davidcrespo.template.presentation.navigation.homeNavigation
import com.davidcrespo.template.presentation.navigation.settingsNavigation

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack<NavKey>(Home)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator() // para que los viewmodels sobrevivan solo durante el tiempo de vida de la entry a la que están asociados
        ),
        transitionSpec = { // navegación hacia adelante
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally()
            )
        },
        popTransitionSpec = { // navegación hacia atrás
            ContentTransform(
                slideInHorizontally(),
                slideOutHorizontally(targetOffsetX = { it })
            )
        },
        entryProvider = entryProvider {
            homeNavigation(backStack)
            settingsNavigation(backStack)
            /* Otra forma de inyectar el viewmodel con Koin y pasarle parámetros
            entry<Theme> { key ->
                ThemeScreen(
                    viewModel = viewModel { SettingsViewModel(key) },
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }*/
        }
    )
}
