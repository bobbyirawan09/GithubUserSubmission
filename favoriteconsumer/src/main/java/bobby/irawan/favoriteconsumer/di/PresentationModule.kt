package bobby.irawan.githubuser.di

import bobby.irawan.githubuser.presentation.favorite.FavoriteConsumerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        FavoriteConsumerViewModel()
    }
}