package brown.jeff.yelpdemo.util

import brown.jeff.yelpdemo.network.NetworkConnection
import brown.jeff.yelpdemo.network.Repository
import brown.jeff.yelpdemo.network.RetrofitClient
import brown.jeff.yelpdemo.view.BusinessAdapter
import brown.jeff.yelpdemo.viewmodel.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module(override = true) {
    //depicts a singleton component
    single {
        Repository(RetrofitClient.businessApi)
    }

    viewModel {
        SearchResultViewModel(get(), get(), get())
    }
    factory {
        NetworkConnection(get())
    }

    single {
        BusinessAdapter(get())
    }
}



