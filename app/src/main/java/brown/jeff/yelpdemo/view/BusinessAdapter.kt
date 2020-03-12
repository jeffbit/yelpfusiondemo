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
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

class BusinessAdapter(private var businesses: List<Business>) :
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

        holder.bind(business)
    }

    fun setBusinessList(businesses: List<Business>) {
        Timber.e("List Updated")
        this.businesses = businesses
        notifyDataSetChanged()
    }
//    //used with viewmodel mutablelist to update each item in list when observed
//    fun updateBusiness(business: Business) {
//        businesses.set(businesses.indexOf(business),business)
//        notifyItemChanged(businesses.indexOf(business))
//    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val businessImage: ImageView = itemView.findViewById(R.id.business_imageview)
        private val businessReview: TextView = itemView.findViewById(R.id.business_review)

        fun bind(business: Business) {
            //business review should be posted here
            businessReview.text = business.name

            Glide.with(businessImage.context)
                .load(business.imageUrl)
                .error(R.drawable.ic_broken_image_black_24dp)
                .apply(RequestOptions.centerCropTransform())
                .into(businessImage)


        }


    }


}
