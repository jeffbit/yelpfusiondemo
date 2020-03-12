package brown.jeff.yelpdemo.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import brown.jeff.yelpdemo.R
import brown.jeff.yelpdemo.util.LOCATION
import brown.jeff.yelpdemo.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.search_result_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResult : Fragment() {
    private val viewModel by viewModel<SearchResultViewModel>()
    private val businessAdapter = BusinessAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.search_result_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        searchView(menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        businessRV.apply {
            layoutManager = GridLayoutManager(context, 2)

            setHasFixedSize(true)
            adapter = businessAdapter

        }


        observeBusinessList()
        observeLoadingScreen()
        observeError()


    }


    private fun observeBusinessList() {
        viewModel.businessMutableList.observe(viewLifecycleOwner, Observer {
            businessAdapter.setBusinessList(it)
        })

    }

    private fun observeLoadingScreen() {
        viewModel.loadingScreen.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun observeError() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                errorMessageTV.text = it
                errorMessageTV.visibility = View.VISIBLE
            } else {
                errorMessageTV.visibility = View.GONE
            }
        })
    }

    //method to observe emitted data from rxjava observable
    private fun observeBusiness() {
        viewModel.businessList.observe(viewLifecycleOwner, Observer {
            businessAdapter.setBusinessList(it)
        })
    }


    private fun searchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //anymore than 4 requests return http 429 error. receiving too many requests
                //not sure how to handle besides trying to add a throttle interceptor
                viewModel.mergedSearch(query!!, LOCATION, 4)
//                viewModel.searchCalls(query!!, LOCATION, 20)
                searchView.setQuery(query, false)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

}
