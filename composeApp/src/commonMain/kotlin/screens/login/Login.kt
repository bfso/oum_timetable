package screens.login

interface Login {
    fun login(username: String, password: String): Boolean
}