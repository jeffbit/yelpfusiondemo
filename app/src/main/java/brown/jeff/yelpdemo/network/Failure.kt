package brown.jeff.yelpdemo.network

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object UnknownException : Failure()

    abstract class FeatureFailure : Failure()


}