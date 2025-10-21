package com.example.musicdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform