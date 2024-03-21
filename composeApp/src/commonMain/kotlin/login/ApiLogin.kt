package login

import network.ApiService

object ApiLogin:Login {
    override fun login(username: String, password: String): Boolean {
        return false
        //return ApiService().login(username,password)
    }
}