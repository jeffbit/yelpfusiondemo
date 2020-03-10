package brown.jeff.yelpdemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import brown.jeff.yelpdemo.R
import brown.jeff.yelpdemo.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.search_result_fragment.*

class SearchResult : Fragment() {
    private lateinit var viewModel: SearchResultViewModel
    private lateinit var adapter: BusinessAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_result_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        business_recyclerview.layoutManager = GridLayoutManager(context, 2)
        business_recyclerview.adapter = adapter


    }

}
