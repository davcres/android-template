package com.davidcrespo.template.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davidcrespo.domain.models.theme.ThemeMode
import com.davidcrespo.template.core.ui.Button
import com.davidcrespo.template.core.ui.auxiliary.ButtonStyle
import com.davidcrespo.template.presentation.ui.theme.TemplateTheme
import com.davidcrespo.template.presentation.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ThemeScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val theme by viewModel.themeMode.collectAsStateWithLifecycle()

    TemplateTheme(themeMode = theme) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("THEME")
                Button(
                    text = "Toogle Theme",
                    onClick = {
                        when (theme) {
                            ThemeMode.LIGHT -> viewModel.setTheme(ThemeMode.DARK)
                            ThemeMode.DARK -> viewModel.setTheme(ThemeMode.SYSTEM)
                            ThemeMode.SYSTEM -> viewModel.setTheme(ThemeMode.LIGHT)
                        }
                    },
                    buttonStyle = ButtonStyle.PRIMARY,
                    buttonModifier = Modifier.padding(16.dp),
                    contentModifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    text = "Back",
                    onClick = onBack,
                    buttonStyle = ButtonStyle.PRIMARY,
                    buttonModifier = Modifier.padding(16.dp),
                    contentModifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ThemeScreenPreview() {
    ThemeScreen(
        onBack = {}
    )
}
