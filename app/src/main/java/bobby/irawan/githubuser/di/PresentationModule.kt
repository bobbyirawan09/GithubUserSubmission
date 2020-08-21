package bobby.irawan.githubuser.di

import android.content.ContentProvider
import android.widget.RemoteViewsService
import bobby.irawan.githubuser.presentation.alarm.AlarmReceiver
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.DetailUserMapper
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.DetailUserViewModel
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.FollowerMapper
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.FollowingMapper
import bobby.irawan.githubuser.presentation.favorite.viewmodel.FavoriteMapper
import bobby.irawan.githubuser.presentation.favorite.viewmodel.FavoriteViewModel
import bobby.irawan.githubuser.presentation.home.viewmodel.HomeViewModel
import bobby.irawan.githubuser.presentation.home.viewmodel.UserMapper
import bobby.irawan.githubuser.presentation.provider.favoriteProvider
import bobby.irawan.githubuser.presentation.widget.StackRemoteViewsFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 04/07/20.
 */

val presentationModule = module {

    viewModel {
        DetailUserViewModel(get(), get(), get(), get(), get())
    }

    viewModel {
        HomeViewModel(
            get(),
            get()
        )
    }

    viewModel {
        FavoriteViewModel(
            get(), get()
        )
    }

    single {
        UserMapper()
    }

    single {
        DetailUserMapper()
    }

    single {
        FollowingMapper()
    }

    single {
        FollowerMapper()
    }

    single {
        FavoriteMapper()
    }

    single {
        AlarmReceiver()
    }

    single {
        androidApplication().contentResolver
    }

    single<ContentProvider> {
        favoriteProvider()
    }

    single<RemoteViewsService.RemoteViewsFactory> {
        StackRemoteViewsFactory(androidApplication().applicationContext, get())
    }
}