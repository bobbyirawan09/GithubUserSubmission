package bobby.irawan.favoriteconsumer.di

import android.content.Context
import bobby.irawan.githubuser.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.dsl.koinApplication

/**
 * Created by bobbyirawan09 on 21/08/20.
 */

object CustomKoinContext {
    private lateinit var appContext: Context

    val koin: Koin by lazy {
        koinApplication {
            androidContext(appContext)
            modules(presentationModule)
        }.koin
    }

    @Synchronized
    fun init(context: Context) {
        check(!::appContext.isInitialized) { "Already initialized!" }

        appContext = context.applicationContext
    }
}

interface CustomKoinComponent : KoinComponent {
    override fun getKoin(): Koin = CustomKoinContext.koin
}