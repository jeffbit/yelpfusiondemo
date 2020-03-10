package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Business
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusinessApi {


    @GET("/businesses/search")
    suspend fun searchBusiness(
        @Query("term") term: String,
        @Query("location") location: String
    ): Call<List<Business>>
}