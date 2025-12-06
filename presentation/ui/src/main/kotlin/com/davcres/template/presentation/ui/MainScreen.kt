package com.davcres.template.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.rememberNavBackStack
import com.davcres.domain.models.theme.ThemeMode
import com.davcres.template.core.ui.Text
import com.davcres.template.core.ui.theme.Color.Button.ButtonPrimary
import com.davcres.template.presentation.ui.navigation.bottomNavigation.BottomDestinations
import com.davcres.template.presentation.ui.navigation.bottomNavigation.BottomNavigation
import com.davcres.template.presentation.ui.navigation.destinations.BottomNavKey.Home
import com.davcres.template.presentation.ui.theme.TemplateTheme
import com.davcres.template.shared.presentation.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    logout: () -> Unit,
) {
    var themeMode by rememberSaveable { mutableStateOf(ThemeMode.SYSTEM) }

    val bottomBackStack = rememberNavBackStack(Home)

    val destinations = remember { BottomDestinations.entries.toList() }

    val onBack: () -> Unit = {
        if (bottomBackStack.size > 1) {
            bottomBackStack.removeLastOrNull()
        } else {
            bottomBackStack.clear()
            bottomBackStack.add(BottomDestinations.HOME.route)
        }
    }

    val canNavigateBack by remember {
        derivedStateOf {
            bottomBackStack.size > 1 ||
                bottomBackStack.firstOrNull() != BottomDestinations.HOME.route
        }
    }

    val selectedTab by remember {
        derivedStateOf {
            BottomDestinations.fromRoute(bottomBackStack.lastOrNull())
        }
    }

    // Handle Android back button
    BackHandler(enabled = canNavigateBack) {
        onBack()
    }

    TemplateTheme(themeMode = themeMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        AnimatedVisibility(canNavigateBack) {
                            IconButton(onClick = {
                                onBack()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                    contentDescription = stringResource(id = R.string.back)
                                )
                            }
                        }
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = selectedTab.icon,
                                contentDescription = stringResource(selectedTab.idName)
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = stringResource(selectedTab.idName),
                                color = ButtonPrimary,
                                fontSize = 18.sp
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = logout) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                                contentDescription = stringResource(id = R.string.logout)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    destinations.forEach { destination ->
                        NavigationBarItem(
                            selected = destination == selectedTab,
                            onClick = {
                                if (destination == selectedTab) return@NavigationBarItem

                                if (bottomBackStack.contains(destination.route)) {
                                    bottomBackStack.remove(destination.route)
                                }
                                bottomBackStack.add(destination.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = stringResource(destination.idName)
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            BottomNavigation(
                backStack = bottomBackStack,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        logout = {}
    )
}
