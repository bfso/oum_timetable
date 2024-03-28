package network

import network.data.Login
import network.data.LoginSuccess
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/v1/login")
    suspend fun login(@Body body:Login) : Response<LoginSuccess>
}