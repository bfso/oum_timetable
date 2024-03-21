package network

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("endpoint")
    suspend fun getCurrencies() : Response<String>
}