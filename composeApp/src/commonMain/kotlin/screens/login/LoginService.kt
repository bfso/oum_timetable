package screens.login

interface LoginService {
    fun checkLogin(username: String, password: String): Boolean
}