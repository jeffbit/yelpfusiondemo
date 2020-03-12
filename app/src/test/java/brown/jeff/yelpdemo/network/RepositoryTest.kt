package brown.jeff.yelpdemo.network

import brown.jeff.yelpdemo.model.Business
import brown.jeff.yelpdemo.util.BUSINESSES
import brown.jeff.yelpdemo.util.LOCATION
import brown.jeff.yelpdemo.util.TERM
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
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
        repository = Repository(mockBusinessApi, mockNetworkConnection)
    }

    @Test
    fun search_returnNetworkError() {
        runBlocking {
            //given
            given { mockNetworkConnection.isInternetAvailable() }.willReturn(false)
            given { runBlocking { mockBusinessApi.searchBusiness(TERM, LOCATION) } }.willReturn(
                mockBusinesses
            )

            //when
            val response = repository.search(TERM, LOCATION, 2)

            //then

            Assert.assertEquals(Result.NetworkError("No Network Connection"), response)
        }
    }

    @Test
    fun search_returnUnknownException() {
        runBlocking {
            //given
            val businessResponse = BUSINESSES
            given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
            given { mockBusinesses.execute().body() }.willReturn(businessResponse)
            given {
                runBlocking { mockBusinessApi.searchBusiness(TERM, LOCATION) }
            }.willReturn(mockBusinesses)

            //when

            val result = repository.search(TERM, LOCATION, 2)

            //then
            Assert.assertEquals(Result.Success(businessResponse), result)

        }
    }


}