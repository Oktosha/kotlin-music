package com.example.musicdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.iamkonstantin.kotlin.gadulka.rememberGadulkaState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import musicdemo.composeapp.generated.resources.Res
import musicdemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val player = rememberGadulkaState()
            Row {
                Button(
                    onClick = {
                        player.play(
                            "https://download.samplelib.com/wav/sample-12s.wav"
                        )
                    }) {
                    Text("Play")
                }
                Button(
                    onClick = {
                        player.stop()
                    }) {
                    Text("Stop")
                }
            }
        }
    }
}