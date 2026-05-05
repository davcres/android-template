package com.davcres.template.auth.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
fun LoginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                text = "Log In",
                onClick = onLogin,
                buttonStyle = ButtonStyle.PRIMARY,
                buttonModifier = Modifier.padding(16.dp),
                contentModifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(
                text = "Sign Up",
                onClick = onSignUp,
                buttonStyle = ButtonStyle.PRIMARY,
                buttonModifier = Modifier.padding(16.dp),
                contentModifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onLogin = {},
        onSignUp = {}
    )
}
