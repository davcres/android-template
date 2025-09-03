package com.davidcrespo.meet.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davidcrespo.meet.core.ui.Animation
import com.davidcrespo.meet.core.ui.Shadow
import com.davidcrespo.meet.presentation.ui.theme.MeetTheme

@Composable
@Preview
fun App() {
    MeetTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Animation(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            /*Shadow(
                modifier = Modifier
                    .padding(innerPadding)
            )*/

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
