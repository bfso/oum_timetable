package network

import kotlinx.coroutines.runBlocking
import network.data.Login

class ApiService {
    val retrofit = RetrofitHelper.getInstance()
    fun login(email:String, password:String):Boolean{
        val api = retrofit.create(ApiInterface::class.java)
        var responseSuccess:Boolean
        runBlocking{
            responseSuccess =  api.login(Login(email = email, password = password)).code() == 200
        }
        return responseSuccess
    }
}