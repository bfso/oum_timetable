package screens.login

interface LoginService {
    fun checkLogin(email: String, password: String): Boolean
}