package brown.jeff.yelpdemo.util

import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.model.Review
import brown.jeff.yelpdemo.model.Reviews
import io.reactivex.Single

const val AUTHORIZATION = "Authorization"
const val BEARER = "Bearer "
const val TERM = "Burger"
const val LOCATION = "Riverside,CA"
const val RADIUS = "10"
const val BUSINESSID = "HYHh5HtxQABC0R2gKhVKPQ"


val REVIEW = Review("1", 5, "sample review")
val BUSINESS =
    Business("HYHh5HtxQABC0R2gKhVKPQ", "ImageUrl", "Business1", listOf(REVIEW, REVIEW))



