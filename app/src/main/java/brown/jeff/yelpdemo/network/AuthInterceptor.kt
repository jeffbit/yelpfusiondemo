package brown.jeff.yelpdemo.network

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.let {
            //add apikey to properties hidden in gradle
            val newRequest = it.request()
                .newBuilder().addHeader("Authorization", "Bearer +  APIKEY")
                .build()
            return chain.proceed(newRequest)
        }
    }

    //class adds yelp key to each search
}