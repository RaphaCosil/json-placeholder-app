package com.example.json_placeholder_app

import com.example.json_placeholder_app.data.datasource.PostsService
import com.example.json_placeholder_app.data.repository.PostsRepositoryImpl
import com.example.json_placeholder_app.domain.repository.PostsRepository
import com.example.json_placeholder_app.domain.usecase.PostsUseCase
import com.example.json_placeholder_app.presentation.viewmodel.CreatePostViewModel
import com.example.json_placeholder_app.presentation.viewmodel.GetAllPostsViewModel
import com.example.json_placeholder_app.presentation.viewmodel.GetPostViewModel
import com.example.json_placeholder_app.presentation.viewmodel.UpdatePostViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<PostsService> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsService::class.java)
    }
}
val dataModule = module {
    factory<PostsRepository> {
        PostsRepositoryImpl(get())
    }
}

val domainModule = module {
    factory {
        PostsUseCase(get())
    }
}

val presentationModule = module {
    viewModel {
        GetAllPostsViewModel(get())
    }

    viewModel {
        GetPostViewModel(get())
    }

    viewModel {
        CreatePostViewModel(get())
    }

    viewModel {
        UpdatePostViewModel(get())
    }
}