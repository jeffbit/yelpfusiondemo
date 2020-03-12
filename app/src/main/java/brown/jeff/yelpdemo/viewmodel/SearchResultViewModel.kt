package brown.jeff.yelpdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import brown.jeff.yelpdemo.R
import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.network.NetworkConnection
import brown.jeff.yelpdemo.network.Repository
import io.reactivex.Observable
import io.reactivex.Observable.fromIterable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchResultViewModel(
    application: Application,
    private val repository: Repository,
    private val networkConnection: NetworkConnection
) :
    AndroidViewModel(application) {

    //mutablelist value
    private val _businessMutableList = MutableLiveData<MutableList<Business>>()
    val businessMutableList: LiveData<MutableList<Business>>
        get() = _businessMutableList

    private val _businessList = MutableLiveData<List<Business>>()
    val businessList: LiveData<List<Business>>
        get() = _businessList

    private val _business = MutableLiveData<Business>()
    val business: LiveData<Business>
        get() = _business

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean>
        get() = _loadingScreen

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private var disposable: Disposable? = null
    private var compositeDisposable: CompositeDisposable? = null


    //function calls business and works properly in recyclerview
    fun searchCalls(term: String, location: String, limit: Int) {
        _loadingScreen.value = true
        when (networkConnection.isInternetAvailable()) {
            true -> {

                val result = repository.searchObservable(term, location, limit)
                disposable = result.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {
                        _loadingScreen.value = false
                        mergedSearch(term, location, limit)
                        _businessList.value = it.businesses
                    }.subscribe()
            }
            false -> {
                _loadingScreen.value = false
                _errorMessage.value = R.string.no_network.toString()
            }
        }
    }


    //only returns a single business but with review attached
    //cant figure out how to return list of businesses
    fun mergedSearch(term: String, location: String, limit: Int) {
        searchBusiness(term, location, limit)
            .subscribeOn(Schedulers.io())
            .flatMap { business -> getReviews(business) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Business> {
                override fun onComplete() {
                    Timber.i("onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Timber.i("onSubscribe")
                    compositeDisposable?.add(d)
                }


                override fun onError(e: Throwable) {
                    Timber.e(e)
                }

                override fun onNext(t: Business) {
                    //Logger shows that multiple businesses are being observed
                    //having problem adding the business data into the adapter list
                    _businessMutableList.value?.add(t)
                    _business.value = t
                    Timber.e("Business: ${t.name}")
                }
            })


    }


    //returns single business in order to get business id for reviews
    private fun searchBusiness(
        term: String,
        location: String,
        limit: Int
    ): Observable<Business> {
        return repository.searchObservable(term, location, limit).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { businesses -> businesses.businesses }
            .flatMap { business -> fromIterable(business) }.map { it }

    }


    //gets the reviews and returns them to the business object reviewList
    private fun getReviews(business: Business): Observable<Business> {
        return repository.getReviewsObservable(business.id).toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { review -> review.reviews }
            .map { business.reviews = it }
            .map { business }


    }


    override fun onCleared() {
        disposable?.dispose()
        compositeDisposable?.dispose()
        super.onCleared()
    }

}








