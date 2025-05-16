✅ Todo List App

A modern Android Todo List app built with Jetpack Compose, following MVVM architecture and an offline-first data strategy. It integrates data from the JSONPlaceholder API and uses Room Database for local storage.

🔹 Key Features

Fetch todos from a REST API using Retrofit

Cache data locally using Room

Works offline with previously loaded data

Clean architecture: MVVM + Repository

Built with Jetpack Compose and Material 3

Smooth navigation between list and detail views

Handles loading and error states gracefully

🛠 Tech Stack

UI: Jetpack Compose

Architecture: MVVM + Repository

Network: Retrofit + Coroutines

Local Storage: Room

State Management: Kotlin Flow / StateFlow

🗂 Project Structure

bash
Copy
Edit
com.example.todo/
├── data/          # API, Room, Repository
├── model/         # Data models
├── ui/            # Compose screens, ViewModel
└── TodoApplication.kt

🚀 How to Run

Clone the project

Open in Android Studio

Run on an emulator or Android device

