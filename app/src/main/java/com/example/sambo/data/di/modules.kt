package com.example.sambo.data.di

import com.example.sambo.data.remote.CoursesService
import com.example.sambo.data.remote.RetrofitBuilder
import com.example.sambo.data.interactors.SamboInteractor
import com.example.sambo.data.interactors.SamboInteractorImpl
import com.example.sambo.data.repository.SamboRepository
import com.example.sambo.data.repository.SamboRepositoryImpl
import com.example.sambo.ui.bottomnavigation.courses.CoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModelModule: Module = module {
   viewModel { CoursesViewModel(get()) }
}


val repositoryModule: Module = module {
    single<SamboRepository> { SamboRepositoryImpl(get()) }
}

val apiModule: Module = module {
    single<CoursesService> { RetrofitBuilder.buildRetrofit() }
    single<SamboInteractor> { SamboInteractorImpl(get()) }
}

val appModules =
    listOf(viewModelModule, apiModule, repositoryModule)