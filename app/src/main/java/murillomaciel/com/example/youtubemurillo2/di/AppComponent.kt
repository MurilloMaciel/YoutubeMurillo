package murillomaciel.com.example.youtubemurillo2.di

import murillomaciel.com.example.youtubemurillo2.model.YoutubeApiService
import murillomaciel.com.example.youtubemurillo2.model.YoutubeApiServiceImpl
import murillomaciel.com.example.youtubemurillo2.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appComponent: List<Module> = listOf(
        module {
                single<YoutubeApiService> { YoutubeApiServiceImpl() }
                viewModel { MainViewModel(get()) }
        }
)