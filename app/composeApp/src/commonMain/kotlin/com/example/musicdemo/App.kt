package com.example.musicdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eu.iamkonstantin.kotlin.gadulka.GadulkaPlayerState
import eu.iamkonstantin.kotlin.gadulka.isPlaying
import eu.iamkonstantin.kotlin.gadulka.rememberGadulkaLiveState
import eu.iamkonstantin.kotlin.gadulka.rememberGadulkaState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import musicdemo.composeapp.generated.resources.Res
import musicdemo.composeapp.generated.resources.compose_multiplatform

enum class Track(val title: String, val url: String) {
    ENGLISH("English", "https://github.com/Oktosha/kotlin-music/raw/refs/heads/main/music-files/english-count-to-10.mp3"),
    SPANISH("Spanish", "https://github.com/Oktosha/kotlin-music/raw/refs/heads/main/music-files/spanish-count-to-10.mp3"),
    DUTCH("Dutch", "https://github.com/Oktosha/kotlin-music/raw/refs/heads/main/music-files/dutch-count-to-10.mp3"),
}

@Composable
@Preview
fun App() {
    Column {
        val gadulka = rememberGadulkaLiveState()
        var activeTrack by remember {  mutableStateOf(Track.ENGLISH) }
        var sliderValueRaw by remember { mutableStateOf(0F) }
        Text("This app can play 3 audio tracks: counting to 10 in English, Spanish and Dutch.")
        Text(gadulka.state.name)

        Text("Volume: ${gadulka.volume}")

        Text("Position: ${gadulka.position / 1000}s / ${gadulka.duration / 1000}s")
        Text("Slider value raw ${sliderValueRaw}")
        Row {
            for (track in Track.entries) {
                Button(
                    onClick = {
                        activeTrack = track
                        gadulka.player.play(track.url)
                    }) {
                    Text(if (activeTrack == track) track.title.uppercase() else track.title)
                }
            }
        }
        Row {
            Text("Playback position: ")
            Slider(gadulka.position.toFloat() / if (gadulka.duration.toFloat() == 0F) 1F else gadulka.duration.toFloat(),
                onValueChange = {
                    sliderValueRaw = it
                },
                onValueChangeFinished = {
                val position = (sliderValueRaw * gadulka.duration).toLong()
                gadulka.player.seekTo(position)
            })
        }
        Row {
            Button(
                onClick = {
                    if (gadulka.position > 0)
                        gadulka.player.play()
                    else
                        gadulka.player.play(activeTrack.url)
                }) {
                Text("Play")
            }
            Button(
                onClick = {
                    gadulka.player.pause()
                }) {
                Text("Pause")
            }
            Button(
                onClick = {
                    gadulka.player.play(activeTrack.url);
                    gadulka.player.pause();
                }) {
                Text("Stop")
            }
        }
    }
}