# Kotlin Multiplatform Music Player

A minimalisic "music" player with Kotlin Multiplatoform that can play 3 predefined tracks. Builds for Android, iOS and web.

## Limitations

The seek functionality works only on Android.

## Implementation Notes

I used [Gadulka](https://klibs.io/project/kkostov/gadulka) library for music player functionality.

I play the tracks by retrieving them from an URL. That means that INTERNET permission on Android is required (and the access to the internet is required).
