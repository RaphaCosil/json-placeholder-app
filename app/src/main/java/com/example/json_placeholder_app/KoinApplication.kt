package com.example.json_placeholder_app

import com.example.data.datasource.Service
import com.example.data.repository.AppRepositoryImpl
import com.example.domain.repository.AppRepository
import com.example.domain.usecase.CreateAlbumUseCase
import com.example.domain.usecase.CreatePhotoUseCase
import com.example.domain.usecase.CreatePostUseCase
import com.example.domain.usecase.GetAlbumsByUserIdUseCase
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetFeedListUseCase
import com.example.domain.usecase.GetPhotosUseCase
import com.example.domain.usecase.GetPostCommentsUseCase
import com.example.domain.usecase.GetPostsByUserIdUseCase
import com.example.domain.usecase.GetUserByIdUseCase
import com.example.json_placeholder_app.presentation.viewmodel.AlbumsOfUserViewModel
import com.example.json_placeholder_app.presentation.viewmodel.CommentsOfUserViewModel
import com.example.json_placeholder_app.presentation.viewmodel.CreateAlbumViewModel
import com.example.json_placeholder_app.presentation.viewmodel.CreatePostViewModel
import com.example.json_placeholder_app.presentation.viewmodel.FindUsersViewModel
import com.example.json_placeholder_app.presentation.viewmodel.HomeViewModel
import com.example.json_placeholder_app.presentation.viewmodel.PostsOfUserViewModel
import com.example.json_placeholder_app.presentation.viewmodel.UserProfileViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Service> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }
}

val dataModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(get())
    }
}

val domainModule = module {
    factory {
        GetFeedListUseCase(get())
    }
    factory {
        GetPostCommentsUseCase(get())
    }
    factory {
        GetPostsByUserIdUseCase(get())
    }
    factory {
        CreatePostUseCase(get())
    }
    factory {
        GetAlbumsByUserIdUseCase(get())
    }
    factory {
        CreateAlbumUseCase(get())
    }
    factory {
        GetAllUsersUseCase(get())
    }
    factory {
        GetUserByIdUseCase(get())
    }
    factory {
        GetPhotosUseCase(get())
    }
    factory {
        CreatePhotoUseCase(get())
    }
}

val presentationModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel{
        CommentsOfUserViewModel(get())
    }
    viewModel {
        CreatePostViewModel(get())
    }
    viewModel {
        CreateAlbumViewModel(get(), get())
    }
    viewModel{
        FindUsersViewModel(get())
    }
    viewModel{
        UserProfileViewModel(get())
    }
    viewModel{
        AlbumsOfUserViewModel(get())
    }
    viewModel{
        PostsOfUserViewModel(get())
    }
}
