package com.davidcrespo.meet.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.meet.presentation.ui.theme.MeetTheme
import com.davidcrespo.meet.presentation.viewmodels.SettingsViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data object Theme: NavKey

@Composable
@Preview
fun ThemeScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val theme by viewModel.themeMode.collectAsStateWithLifecycle()

    MeetTheme(themeMode = theme) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("THEME")
                Button(
                    onClick = {
                        when (theme) {
                            ThemeMode.LIGHT -> viewModel.setTheme(ThemeMode.DARK)
                            ThemeMode.DARK -> viewModel.setTheme(ThemeMode.SYSTEM)
                            ThemeMode.SYSTEM -> viewModel.setTheme(ThemeMode.LIGHT)
                        }
                    }
                ) {
                    Text("Toggle Theme")
                }
            }
        }
    }
}
