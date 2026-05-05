package com.davcres.template.auth.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davcres.template.core.ui.Button
import com.davcres.template.core.ui.auxiliary.ButtonStyle

@Composable
fun HomeScreen(
    onClick: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                text = "Go to Settings",
                onClick = onClick,
                buttonStyle = ButtonStyle.PRIMARY,
                buttonModifier = Modifier.padding(16.dp),
                contentModifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onClick = {}
    )
}
