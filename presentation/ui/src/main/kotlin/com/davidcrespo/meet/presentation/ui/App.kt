package com.davidcrespo.meet.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidcrespo.meet.presentation.ui.theme.MeetTheme

@Composable
@Preview
fun App() {
    MeetTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var showContent by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    //.safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = {
                    showContent = !showContent
                    println("*** start scanning ***")
                    //startScanning(getPlatformContext())
                }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        //Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Greeting("HOLA")
                    }
                }
            }
        }
    }
}