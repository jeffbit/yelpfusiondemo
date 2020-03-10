package brown.jeff.yelpdemo.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val URL: String = "https://api.yelp.com/v3/"
    private val authInterceptor = AuthInterceptor()


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor).build()

    private fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val businessApi = retrofit().create(BusinessApi::class.java)
}