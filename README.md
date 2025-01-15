# Json-Placeholder-App

## Project Overview

This is a mobile application built using Android with an emphasis on modular architecture, leveraging the MVVM (Model-View-ViewModel) design pattern. 

The app simulates a social network, managing albums, posts, and user interactions using various endpoints from the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API. 

The project serves as a template for applying architectural principles in other projects.

Key features include:

- RecyclerViews
- Retrofit for API interactions
- ViewModels
- State and Action Management
- Dependency Injection with Koin
- Clean Architecture
- Modularization

---

## Architecture

The project follows Clean Architecture, organized into three key layers:

### 1. Presentation Layer (app module)

This layer handles the UI components, including activities, fragments, and ViewModels. 

It communicates with the Domain Layer to fetch and update data. 

The ViewModel observes states and updates the UI accordingly.

- UI (Activities & Fragments):
    
    Displays user-facing elements such as lists, forms, and navigation.
    
- ViewModels:
    
    Manages UI state and interacts with UseCases from the Domain Layer.
    
- Utilities:
    
    Helper classes for UI enhancements like RecyclerView decorations or utility functions.
    

### 2. Domain Layer (domain module)

This layer defines the business logic and use cases. 

It abstracts the application rules and interacts with repositories in the Data Layer.

- Entities:
    
    Business objects representing core data.
    
- UseCases:
    
    Define application-specific operations (e.g., fetching posts, creating albums).
    
- Repositories (Interfaces):
    
    Abstracts data sources, ensuring the Domain Layer doesn't depend directly on the Data Layer.
    

### 3. Data Layer (data module)

This layer is responsible for data management. 

It connects with APIs and transforms data into domain entities.

- DataSource:
    
    Contains the Retrofit service interface for API calls.
    
- Models:
    
    Data Transfer Objects (DTOs) used for serialization, deserialization and transformation of the DTOs to Entity.
    
- Repository Implementation:
    
    Implements the repository interfaces and handles data transformations.
    

---

## Project Structure

```
├── app
|   └── src/main/java/com/example/json_placeholder_app/presentation
|       ├── ui
|       │   ├── activity
|       │   ├── fragment
|       │   ├── view
|       │   ├── utils
|       └── viewmodel
|           └── state
|           └── action
|
├── data
|   └── src/main/java/com/example/data
|       ├── datasource
|       ├── model
|       └── repository
|
└── domain
    └── src/main/java/com/example/domain
        ├── entity
        ├── repository
        └── usecase
```

---

## Key Functionalities

### User Interaction

- FindUsersFragment: Lists all users and navigates to a specific user’s profile.
- HomeFragment: Displays the main feed (e.g., posts, albums).

### Albums Management

- AlbumsOfUserFragment: Displays a list of albums for a user.
- CreateAlbumFragment: Allows users to create albums with titles and up to two photos.

### Post Management

- CreatePostFragment: Enables users to create posts with a title and description.

### Publication Management

- CreatePublicationFragment: Manages switching between creating posts and albums.

---

## Tech Stack

- Programming Language: Kotlin
- Architecture: MVVM with Clean Architecture
- Networking: [Retrofit](https://square.github.io/retrofit/) + [Gson](https://github.com/google/gson)
- Dependency Injection: [Koin](https://insert--koin-io.translate.goog/?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc)
- State Management: LiveData
- UI Components: RecyclerView, Fragments, ViewBinding
- API: [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
