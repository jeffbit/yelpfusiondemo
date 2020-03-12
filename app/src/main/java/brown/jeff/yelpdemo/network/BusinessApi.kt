package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Businesses
import brown.jeff.yelpdemo.model.Reviews
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BusinessApi {


//    @GET("businesses/search?")
//    fun searchBusiness(
//        @Query("term") term: String,
//        @Query("location") location: String,
//        @Query("limit") limit: Int
//    ): Call<Businesses>
//
//    @GET("businesses/{id}/reviews")
//    fun getBusinessReviews(@Path("id") id: String):
//            Call<Reviews>

    @GET("businesses/search?")
    fun searchBusinessObservable(
        @Query("term") term: String,
        @Query("location") location: String,
        @Query("limit") limit: Int
    ): Single<Businesses>


    @GET("businesses/{id}/reviews")
    fun getBusinessReviewsObservable(@Path("id") id: String):
            Single<Reviews>


}