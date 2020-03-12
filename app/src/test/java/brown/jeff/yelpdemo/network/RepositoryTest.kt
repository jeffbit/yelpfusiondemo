package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.util.BUSINESS
import brown.jeff.yelpdemo.util.LOCATION
import brown.jeff.yelpdemo.util.REVIEW
import brown.jeff.yelpdemo.util.TERM
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

//repository testing for when using retrofit call
class RepositoryTest {
    private lateinit var repository: Repository
    private lateinit var mockBusinessApi: BusinessApi
    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var mockBusinesses: Call<List<Business>>


    @Before
    fun setup() {
        mockBusinessApi = mock()
        mockNetworkConnection = mock()
        mockBusinesses = mock()
        repository = Repository(mockBusinessApi)
    }


    @Test
    fun search_returnSingle_True() {
        //given
        doReturn(Single.just(BUSINESS)).`when`(mockBusinessApi)
            .searchBusinessObservable(TERM, LOCATION, 2)
        //when
        val testObserver = repository.searchObservable(TERM, LOCATION, 2).test()
        //then
        testObserver.assertOf { BUSINESS }
        testObserver.dispose()

    }




//    @Test
//    fun search_returnNetworkError() {
//        runBlocking {
//            //given
//            given { mockNetworkConnection.isInternetAvailable() }.willReturn(false)
//            given { runBlocking { mockBusinessApi.searchBusiness(TERM, LOCATION) } }.willReturn(
//                mockBusinesses
//            )
//
//            //when
//            val response = repository.search(TERM, LOCATION, 2)
//
//            //then
//
//            Assert.assertEquals(Result.NetworkError("No Network Connection"), response)
//        }
//    }
//
//    @Test
//    fun search_returnUnknownException() {
//        runBlocking {
//            //given
//            val businessResponse = BUSINESSES
//            given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
//            given { mockBusinesses.execute().body() }.willReturn(businessResponse)
//            given {
//                runBlocking { mockBusinessApi.searchBusiness(TERM, LOCATION) }
//            }.willReturn(mockBusinesses)
//
//            //when
//
//            val result = repository.search(TERM, LOCATION, 2)
//
//            //then
//            Assert.assertEquals(Result.Success(businessResponse), result)
//
//        }
//    }


}