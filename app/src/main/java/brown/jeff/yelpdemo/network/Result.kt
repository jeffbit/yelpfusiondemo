package brown.jeff.yelpdemo.network

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class UnknownException(val exception: Exception) : Result<Nothing>()
    data class NetworkError(val error: String) : Result<Nothing>()
    data class ServerError(val error: String) : Result<Nothing>()
}