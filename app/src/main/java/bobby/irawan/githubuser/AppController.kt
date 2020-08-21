package bobby.irawan.githubuser

import android.app.Application
import android.content.Context
import bobby.irawan.githubuser.di.dataModule
import bobby.irawan.githubuser.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by bobbyirawan09 on 12/07/20.
 */
class AppController : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(dataModule, presentationModule)
        }
    }

    companion object {
        private lateinit var instance: AppController

        fun getInstance(): Context = instance.applicationContext
    }
}