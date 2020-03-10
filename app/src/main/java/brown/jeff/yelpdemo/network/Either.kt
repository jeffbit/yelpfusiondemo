package brown.jeff.yelpdemo.network


//Left is used for Failure
//Right is used for success
sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()


    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>


}