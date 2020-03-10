package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.network.Either.Left
import brown.jeff.yelpdemo.network.Either.Right

class Repository(
    private val businessApi: BusinessApi,
    private val networkConnection: NetworkConnection
) {


    suspend fun search(term: String, location: String): Either<Failure, List<Business>> {
        return when (networkConnection.isInternetAvailable()) {
            true -> {
                return try {
                    val result = businessApi.searchBusiness(term, location).execute()
                    when (result.isSuccessful && result.body() != null) {
                        true -> Right(result.body()!!)
                        false -> Left(Failure.ServerError)
                    }
                } catch (e: Exception) {
                    Left(Failure.UnknownException)
                }
            }
            false ->
                Left(Failure.NetworkConnection)
        }

    }

//    private fun <T> safeCall(call: Call<T>): Either<Failure, T> {
//        return try {
//            val result = call.execute()
//            when (result.isSuccessful && result.body() != null) {
//                true -> Right(result.body()!!)
//                false -> Left(Failure.ServerError)
//            }
//
//        } catch (e: Exception) {
//            Left(Failure.UnknownException)
//        }
//    }
}


