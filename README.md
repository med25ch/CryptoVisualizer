# CryptoVisualizer

CryptoVisualizer is an Android app built with Kotlin, Jetpack Compose, and Clean Architecture. It displays cryptocurrency prices through interactive charts, offering a modern and intuitive user experience.

## Features

- Fetches and displays real-time cryptocurrency prices using Ktor for API calls and Coroutines for asynchronous operations.
- Interactive Chart Visualization.

## Tech Stack

UI :
- Jetpack Compose: For building the UI declaratively.
- Material 3: For adhering to modern Material Design guidelines.

Architecture :
- Clean Architecture: Separation of concerns through domain, data, and presentation layers.
- MVVM (Model-View-ViewModel): For structuring the app and managing UI-related data.
- ViewModel: For managing UI-related data lifecycle-conscious.

Data & Backend : 
- Room Database: For local data persistence.
- Ktor: For making network requests to the Coincap API.
- Coroutines & Flow: For managing asynchronous tasks and data streams in a clean, concise way.

Dependency Injection
- Koin: For managing dependency injection efficiently across the app.

## Screenshots

<img src="https://github.com/med25ch/CryptoVisualizer/blob/master/screenshots/Screenshot_1.png" width="30%"></img> <img src="https://github.com/med25ch/CryptoVisualizer/blob/master/screenshots/Screenshot_2.png" width="30%"></img>
<img src="https://github.com/med25ch/CryptoVisualizer/blob/master/screenshots/Screenshot_3.png" width="30%"></img> <img src="https://github.com/med25ch/CryptoVisualizer/blob/master/screenshots/Screenshot_4.png" width="30%"></img>
