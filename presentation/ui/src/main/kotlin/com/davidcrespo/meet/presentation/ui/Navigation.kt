package com.davidcrespo.meet.presentation.ui

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.davidcrespo.meet.presentation.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation() {

    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator() // para que los viewmodels sobrevivan solo durante el tiempo de vida de la entry a la que están asociados
        ),
        transitionSpec = { //navegación hacia adelante
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally()
            )
        },
        popTransitionSpec = { //navegación hacia atrás
            ContentTransform(
                slideInHorizontally(),
                slideOutHorizontally(targetOffsetX = { it })
            )
        },
        entryProvider = entryProvider {
            entry<Home> {
                HomeScreen(
                    onClick = {
                        backStack.add(Theme)
                    },
                )
            }
            entry<Theme> {
                ThemeScreen()
            }
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
