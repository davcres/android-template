package com.davcres.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davcres.template.appRoot.presentation.ui.navigation.MainNavigation
import com.davcres.template.appRoot.presentation.viewmodels.MainViewModel
import com.davcres.template.core.ui.theme.TemplateTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()

            TemplateTheme(themeMode = themeMode) {
                MainNavigation()
            }
        }
    }
}
