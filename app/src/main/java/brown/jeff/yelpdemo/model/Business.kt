package brown.jeff.yelpdemo.model

import com.google.gson.annotations.SerializedName

data class Business(
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    var reviews: List<Review>
) {
}