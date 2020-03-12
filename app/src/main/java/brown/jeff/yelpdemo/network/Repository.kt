package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Businesses
import brown.jeff.yelpdemo.model.Reviews
import io.reactivex.Single

class Repository(
    private val businessApi: BusinessApi, private val networkConnection: NetworkConnection
) {

    fun searchObservable(term: String, location: String, limit: Int): Single<Businesses> {
        return businessApi.searchBusinessObservable(term, location, limit)
    }

    fun getReviewsObservable(id: String): Single<Reviews> {
        return businessApi.getBusinessReviewsObservable(id)
    }

//    // original  fun using result wrapper to handle errors in data
//    // was trying to use fun with livedata transform to map the api calls together
//
//    fun search(term: String, location: String, limit: Int): Result<Businesses> {
//        return when (networkConnection.isInternetAvailable()) {
//            true -> safeCall(businessApi.searchBusiness(term, location, limit))
//            false -> Result.NetworkError("No Network Connection")
//
//        }
//    }
//
//
//    suspend fun getBusinessReview(businessId: String): Result<Reviews> {
//        return withContext(Dispatchers.IO) {
//            when (networkConnection.isInternetAvailable()) {
//                true -> safeCall(businessApi.getBusinessReviews(businessId))
//                false -> Result.NetworkError("No Network Connection")
//            }
//        }
//    }
//
//
//    private fun <T> safeCall(call: Call<T>): Result<T> {
//        return try {
//            val result = call.execute()
//            when (result.isSuccessful && result.body() != null) {
//                true -> Result.Success(result.body()!!)
//                false -> Result.ServerError(result.code().toString())
//            }
//
//        } catch (e: Exception) {
//            Result.UnknownException(e)
//        }
//    }

}




