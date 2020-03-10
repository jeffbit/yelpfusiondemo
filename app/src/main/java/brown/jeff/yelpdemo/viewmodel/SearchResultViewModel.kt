package brown.jeff.yelpdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.network.Either
import brown.jeff.yelpdemo.network.Repository
import kotlinx.coroutines.launch

class SearchResultViewModel(private val repository: Repository) : ViewModel() {

    private val _businesses = MutableLiveData<List<Business>>()
    val businesses: LiveData<List<Business>>
        get() = _businesses

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean>
        get() = _loadingScreen

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private fun search(term: String, location: String) {
        _loadingScreen.value = true
        viewModelScope.launch {
            when (val result = repository.search(term, location)) {
                is Either.Right -> {
                    _businesses.value = result.b
                    _loadingScreen.value = false
                }
                is Either.Left -> {
                    _errorMessage.value = result.a.toString()
                    _loadingScreen.value = false

                }

            }
        }
    }

}




