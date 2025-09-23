# jwt-sample-jetpack-compose

<p align="left">
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin&logoColor=white" />
  <img alt="AGP" src="https://img.shields.io/badge/AGP-8.13.0-3DDC84?logo=android&logoColor=white" />
  <img alt="Gradle" src="https://img.shields.io/badge/Gradle-8.13-02303A?logo=gradle&logoColor=white" />
  <img alt="minSdk" src="https://img.shields.io/badge/minSdk-26-blue" />
  <img alt="targetSdk" src="https://img.shields.io/badge/targetSdk-36-brightgreen" />
  <img alt="Compose" src="https://img.shields.io/badge/Compose-BOM_2024.09.00-4285F4?logo=jetpackcompose&logoColor=white" />
  <img alt="Hilt" src="https://img.shields.io/badge/Hilt-2.57.1-34A853?logo=android&logoColor=white" />
  <img alt="Retrofit" src="https://img.shields.io/badge/Retrofit-3.0.0-2E7D32" />
  <img alt="OkHttp" src="https://img.shields.io/badge/OkHttp-4.12.0-000000" />
</p>

An Android sample app built with Kotlin and Jetpack Compose featuring JWT authentication, dependency injection via Hilt, and a networking stack powered by Retrofit/OkHttp. Includes safe error handling and fake repositories for offline demos and testing.

## Tech stack
- Kotlin 2.0.21, Gradle 8.13, AGP 8.13.0
- Jetpack Compose (BOM 2024.09.00), Material3, Navigation Compose
- Hilt (DI)
- Retrofit, OkHttp, Kotlinx Serialization

## Features
- User registration (screen `RegistrationView`)
- Navigation with a nested `auth` graph (root startDestination = `auth`, nested startDestination = `auth/registration`)
- Fake repositories for quick checks without a backend:
  - `FakeAuthRepository`
  - `FakeTokenStorageRepository`
- Basic error handling via `Result` and mapping exceptions into domain errors

## Quick start
- Min SDK: 26
- Target SDK: 36

Open the project in the latest stable Android Studio, sync Gradle, and run the `app` configuration. To build/run from a terminal, see the "Commands" section below.

## Architecture (short)
- Domain: repository interfaces, use-cases
- Data/DI: Hilt providers and dependency configuration
- Presentation: Compose screens, ViewModels

## Error handling
Use-cases return `Result<T>`; exceptions are mapped to domain errors for convenient UI rendering.

## Commands
Build/run using Gradle Wrapper (Windows):
- Build debug APK: `gradlew.bat assembleDebug`
- Install on a connected device: `gradlew.bat installDebug`
- Run unit tests: `gradlew.bat test`
- Clean project: `gradlew.bat clean`
