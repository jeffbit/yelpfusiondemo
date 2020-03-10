package brown.jeff.yelpdemo.util

import brown.jeff.yelpdemo.network.Repository
import brown.jeff.yelpdemo.network.RetrofitClient
import brown.jeff.yelpdemo.viewmodel.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    //depicts a singleton component
    single {
        Repository(RetrofitClient.businessApi, get())
    }
}

val viewModelSearchResult = module {
    viewModel { SearchResultViewModel(get()) }
}

