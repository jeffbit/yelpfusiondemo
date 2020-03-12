package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.BuildConfig
import brown.jeff.yelpdemo.util.AUTHORIZATION
import brown.jeff.yelpdemo.util.BEARER
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        chain.let {
            val request = chain.request()
            val newRequest = request
                .newBuilder().addHeader(AUTHORIZATION, BEARER + BuildConfig.YelpApiKey)
                .build()
            return chain.proceed(newRequest)
        }


    }
}