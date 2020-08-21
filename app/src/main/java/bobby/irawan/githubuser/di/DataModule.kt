package bobby.irawan.githubuser.di

import androidx.room.Room
import bobby.irawan.githubuser.AppDatabase
import bobby.irawan.githubuser.data.detailuser.DetailUserApi
import bobby.irawan.githubuser.data.detailuser.DetailUserContract
import bobby.irawan.githubuser.data.detailuser.DetailUserImpl
import bobby.irawan.githubuser.data.favorite.FavoriteService
import bobby.irawan.githubuser.data.favorite.FavoriteServiceImpl
import bobby.irawan.githubuser.data.interceptor.TokenInterceptor
import bobby.irawan.githubuser.data.user.UserApi
import bobby.irawan.githubuser.data.user.UserContract
import bobby.irawan.githubuser.data.user.UserImpl
import bobby.irawan.githubuser.utils.Constants.BASE_URL
import bobby.irawan.githubuser.utils.Constants.LOGGING_INTERCEPTOR
import bobby.irawan.githubuser.utils.Constants.TOKEN_INTERCEPTOR
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
val dataModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "favorite_database"
        )
            .build()
    }

    single {
        get<AppDatabase>().favoriteDao()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(qualifier = named(LOGGING_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(qualifier = named(TOKEN_INTERCEPTOR)))
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    single<Interceptor>(named(TOKEN_INTERCEPTOR)) {
        TokenInterceptor()
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(UserApi::class.java) }

    single { get<Retrofit>().create(DetailUserApi::class.java) }

    single<DetailUserContract> {
        DetailUserImpl(get())
    }

    single<FavoriteService> {
        FavoriteServiceImpl(get())
    }

    single<UserContract> {
        UserImpl(get())
    }

}