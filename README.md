# GithubUserSearch

## Description
This is an Android application that allows users to search for GitHub users, view their profiles, and explore related information such as followers, following, and repositories. The app leverages the GitHub API to fetch and display user data in a user-friendly interface, supporting features like user search and detailed profile exploration.

## Features
- **User Search:** Users can search for GitHub users via a search view in the main activity, which queries the GitHub API and displays results in a RecyclerView list.
- **User Details:** Clicking on a user navigates to a detail screen showing profile information, including followers, following, and repositories.
- **Followers and Following:** Displays lists of users who follow or are followed by the selected user.
- **Repositories:** Shows a list of public repositories for the selected user, including details like stars, forks, and descriptions.
- **Offline Support:** Uses Room database for local caching of user data.
- **UI Enhancements:** Includes shimmer effects for loading states, Lottie animations, and circular image views for better user experience.

## Prerequisites
- Android Studio (latest version recommended)
- JDK 11
- Android device or emulator with API level 24+

## Setup and Installation
1. Clone or download the project.
2. Open the project in Android Studio.
3. Sync the Gradle files to download dependencies (Android Studio will prompt this).

## Build and Run Instructions
- **Build the project:**
  ```
  ./gradlew assembleDebug
  ```
  Or use Android Studio's "Build > Make Project".

- **Run on a device/emulator:**
  ```
  ./gradlew installDebug
  ```
  Or use Android Studio's "Run" button.

The project uses Gradle with version catalogs (libs.versions.toml). Compile SDK is 35, min SDK is 24, target SDK is 35. No custom Gradle tasks are defined beyond standard Android builds.

## Dependencies
Key libraries include:
- Retrofit and Gson for API calls
- OkHttp with Chucker for HTTP logging and debugging
- Room for local database
- Hilt for dependency injection
- Glide for image loading
- Lottie for animations
- Shimmer for loading effects
- CircleImageView for UI components

## API Usage
The app integrates with the GitHub REST API (v3) for fetching user data. No API key is required as it uses public endpoints, but users should be aware of GitHub's rate limits (60 requests per hour for unauthenticated requests). The app requires INTERNET permission for API calls.

## Additional Features, Improvements, or Challenges
- **Architecture:** The app follows MVVM pattern with Hilt for DI, which is solid, but error handling in ViewModel and API calls could be enhanced (e.g., adding try-catch blocks and exposing error states via LiveData).
- **UI/UX:** Loading states are handled with shimmer, but more robust error UI (e.g., retry buttons) could improve user experience. The search functionality only triggers on submit, not on text change, which might limit usability.
- **Performance:** Room is used for caching, but no explicit cache invalidation strategy is visible; consider adding TTL or refresh mechanisms.
- **Challenges:** Reliance on public GitHub API may lead to rate limiting; consider adding authentication for higher limits. Code could benefit from more unit tests (only basic ExampleUnitTest.kt exists). Some dependencies are hardcoded versions (e.g., Lottie 4.2.2), which might need updates for security.
- **Security:** No sensitive data handling, but ensure Chucker is only in debug builds to avoid exposing API logs in production.