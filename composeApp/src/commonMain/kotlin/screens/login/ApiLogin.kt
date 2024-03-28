package screens.login

import network.ApiService

object ApiLogin: LoginService {
    override fun checkLogin(email: String, password: String): Boolean {
        return ApiService().login(email, password)
    }
}