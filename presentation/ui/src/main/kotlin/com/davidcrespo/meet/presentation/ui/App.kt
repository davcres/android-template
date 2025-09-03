package com.davidcrespo.meet.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidcrespo.meet.core.ui.Animation
import com.davidcrespo.meet.core.ui.ProgressButton
import com.davidcrespo.meet.core.ui.Shadow
import com.davidcrespo.meet.presentation.ui.theme.MeetTheme

@Composable
@Preview
fun App() {
    MeetTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Animation(
                    Modifier
                        .size(300.dp)
                )

                Shadow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                )

                ProgressButton(
                    text = "Start Scanning",
                    color = Color.Cyan,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    onClick = {
                        println("*** start scanning ***")
                        //startScanning(getPlatformContext())
                    },
                    onProgressComplete = {
                        println("*** scanning complete ***")
                    }
                )
            }

            // var showContent by remember { mutableStateOf(false) }
            /*Column(
                modifier = Modifier
                    .padding(innerPadding)
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
            }*/
        }
    }
}
