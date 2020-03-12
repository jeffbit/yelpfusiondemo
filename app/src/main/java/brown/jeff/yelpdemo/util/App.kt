package brown.jeff.yelpdemo.util

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            Timber.i("Koin Started")
            androidContext(this@App)
            modules(applicationModule)
        }
    }


}