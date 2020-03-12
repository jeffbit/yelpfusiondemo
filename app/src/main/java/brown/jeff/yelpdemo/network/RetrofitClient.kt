package brown.jeff.yelpdemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RetrofitClient {

    private val URL: String = "https://api.yelp.com/v3/"
    private val authInterceptor = AuthInterceptor()
    //interceptor for viewing url
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor).build()

    private fun retrofit(): Retrofit {
        Timber.e("Retrofit Started")
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit
    }


    val businessApi = retrofit().create(BusinessApi::class.java)
}