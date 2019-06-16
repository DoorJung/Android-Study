package com.icoo.androidstudy.di

import com.icoo.androidstudy.data.model.GithubRepoRepository
import com.icoo.androidstudy.data.model.GithubRepoRepositoryImpl
import com.icoo.androidstudy.data.remote.GithubAPI
import com.icoo.androidstudy.ui.main.MainViewModel
import com.icoo.androidstudy.util.AndroidSchedulerProvider
import com.icoo.androidstudy.util.SchedulerProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rxModule = module {
    //provide schedule provider
    factory<SchedulerProvider> { AndroidSchedulerProvider() }
}

val networkModule = module {
    single { GithubAPI.create() }
}

val factoryModule = module {
    factory<GithubRepoRepository> {
        GithubRepoRepositoryImpl(get())
    }
}

val viewModule = module {
    //Main
    viewModel { MainViewModel(get(), get()) }
}

val appModule = listOf(rxModule, networkModule, factoryModule, viewModule)