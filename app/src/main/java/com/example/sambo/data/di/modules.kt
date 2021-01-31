package com.example.sambo.data.di

import com.example.sambo.data.remote.SamboService
import com.example.sambo.data.remote.RetrofitBuilder
import com.example.sambo.data.interactors.SamboInteractor
import com.example.sambo.data.interactors.SamboInteractorImpl
import com.example.sambo.data.repository.SamboRepository
import com.example.sambo.data.repository.SamboRepositoryImpl
import com.example.sambo.ui.bottomnavigation.courses.CoursesViewModel
import com.example.sambo.ui.collectionsdetails.CollectionsDetailsViewModel
import com.example.sambo.ui.bottomnavigation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { CoursesViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { CollectionsDetailsViewModel(get()) }
}

val repositoryModule: Module = module {
    single<SamboRepository> { SamboRepositoryImpl(get()) }
}

val apiModule: Module = module {
    single<SamboService> { RetrofitBuilder.buildRetrofit() }
    single<SamboInteractor> { SamboInteractorImpl(get()) }
}

val appModules =
    listOf(viewModelModule, apiModule, repositoryModule)