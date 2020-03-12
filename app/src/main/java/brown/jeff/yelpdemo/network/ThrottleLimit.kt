package brown.jeff.yelpdemo.network

import android.os.SystemClock
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.Response

class ThrottleLimit : Interceptor {
    //limits the rate of api calls

    private val dispatcher = Dispatcher()

    fun createDispatcher(): Dispatcher {
        dispatcher.maxRequests = 1
        return dispatcher
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.let {
            SystemClock.sleep(1000)
            return chain.proceed(chain.request())

        }
    }
}