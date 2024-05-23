# GitHub User Search App

## Overview
This project is a GitHub User Search app that allows users to search for GitHub users, view a list of search results, and see detailed information about a selected user. The app uses the GitHub API for fetching user data and Room for local data persistence. The project adheres to clean architecture principles, modular code, and best practices.

## Features
### Must Have
1. **Search Screen**:
    - Users can search for GitHub users.
    - Uses Retrofit and OkHttp for networking.
    - Displays search results in a RecyclerView.
    
2. **User List Screen**:
    - Displays a list of GitHub users with usernames and avatars.
    - Uses Glide library for image loading.
    
3. **User Detail Screen**:
    - Displays detailed information about a selected user, including username, avatar, bio, and other details.
    
4. **Local Data Persistence**:
    - Uses Room for local database storage to persist GitHub users.
    - Implements data caching and retrieval.
    
5. **Clean Architecture**:
    - Follows clean architecture pattern (MVVM).
    - Defines layers such as Domain, Data, and Presentation.
    - Uses dependency injection with Hilt.
    
6. **Concurrency**:
    - Implements Kotlin Coroutines for asynchronous operations.
    - Uses Jetpack libraries like LiveData and ViewModel.

7. **Git History**:
    - Maintain a clean and descriptive Git history to track changes.

### Nice to Have
1. **Unit Testing**:
    - Provides coverage of unit tests using JUnit, Espresso, Mockito.
    
2. **Debugging**:
    - Integrates Chucker for debugging during development.
    
3. **Dependency Versioning**:
    - Implements Version Catalog for managing dependency versions.
    
4. **JSON Parsing**:
    - Uses Moshi for JSON parsing.

## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture pattern. This architecture was chosen because it helps in separating concerns, making the codebase easier to maintain and test. The MVVM pattern also allows for better handling of UI-related data using ViewModel and LiveData.

## Dependencies
- **Retrofit**: For networking.
- **OkHttp**: For HTTP client.
- **Glide**: For image loading.
- **Room**: For local data persistence.
- **Hilt**: For dependency injection.
- **Kotlin Coroutines**: For concurrency.
- **LiveData**: For observable data holders.
- **ViewModel**: For UI-related data management.
- **JUnit**: For unit testing.
- **Espresso**: For UI testing.
- **Mockito**:  For unit testing.
- **Chucker**: For network debugging.
- **Moshi**: For JSON parsing.

## Setup Instructions
1. **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/github-user-search.git
    cd github-user-search
    ```
2. **Open the project in Android Studio.**
   
4. **Open app/java/com/githubusers/app/utils/Constants.kt.**

5. **Insert Github Personal Access Token:**
    ```bash
    //Replace with your Github Personal Access Token
    const val personalAccessToken = "*************************************"
    ```

6. **Build the project:**
    - Make sure you have the latest version of Android Studio and the necessary SDKs installed.
    - Sync the project with Gradle files.

7. **Run the app:**
    - Connect an Android device or use an emulator.
    - Click on the "Run" button in Android Studio.

## Additional Features and Improvements
- Enhanced the app with Chucker for better network debugging.
- Utilized Moshi for efficient JSON parsing and improved performance.
- Managed dependencies using Version Catalog for easier version updates.

## Challenges
- Ensuring smooth image loading and caching with Glide.
- Maintaining clean and maintainable code while adhering to MVVM architecture.

## Submission
- GitHub repository link: [GitHub User Search](https://github.com/JackMizh/GithubUsers)
- Backup archive: [Google Drive Link](https://drive.google.com/file/d/11unfgqyO_4tJzmjM479lgXivLQjJ-1qs/view?usp=sharing)
