package bobby.irawan.favoriteconsumer

import android.app.Application
import android.content.Context
import bobby.irawan.favoriteconsumer.di.CustomKoinContext

/**
 * Created by bobbyirawan09 on 12/07/20.
 */
class AppFavoriteController : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        CustomKoinContext.init(this)
    }

    companion object {
        private lateinit var instance: AppFavoriteController

        fun getInstance(): Context = instance.applicationContext
    }
}