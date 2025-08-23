package com.davidcrespo.meet.presentation.ui

import android.R.attr.name
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colorScheme.primaryContainer) {
        Text(
            text = "Hello, $name!",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

// Preview in Android Studio
@Preview()
@Composable
fun GreetingPreview() {
        Text(
            text = "Hello!"
        )
}