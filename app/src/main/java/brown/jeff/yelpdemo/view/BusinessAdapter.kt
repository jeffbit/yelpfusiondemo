package brown.jeff.yelpdemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.yelpdemo.R
import brown.jeff.yelpdemo.model.Business
import com.bumptech.glide.Glide

class BusinessAdapter(private val businesses: List<Business>) :
    RecyclerView.Adapter<BusinessAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.business_list_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return businesses.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val business = businesses[position]

        Glide.with(holder.businessImage.context)
            .load(business.imageUrl)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(holder.businessImage)

        //need to change to top business review
        holder.businessReview.text = business.name
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val businessImage: ImageView = itemView.findViewById(R.id.business_imageview)
        val businessReview: TextView = itemView.findViewById(R.id.business_review)


    }

}