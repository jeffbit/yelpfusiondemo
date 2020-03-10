package brown.jeff.yelpdemo.model

import com.google.gson.annotations.SerializedName

class Business(
     val id: String,
     val name: String,
    @SerializedName("image_url")
     val imageUrl: String
) {
}