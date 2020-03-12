package brown.jeff.yelpdemo.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import brown.jeff.yelpdemo.R
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import java.io.IOException
import java.net.SocketException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())


        //initRxErrorHandler()
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable is UndeliverableException) {
                throwable.cause?.let {
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), it)
                    return@setErrorHandler
                }
            }
            if (throwable is IOException || throwable is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (throwable is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (throwable is NullPointerException || throwable is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread()
                    .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), throwable)
                return@setErrorHandler
            }
            if (throwable is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread()
                    .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), throwable)
                return@setErrorHandler
            }
            Timber.e("Undeliverable exception $throwable")
        }
    }
}
