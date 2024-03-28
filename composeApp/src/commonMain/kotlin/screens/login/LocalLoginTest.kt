package screens.login

object LocalLoginTest: LoginService {

    private val testLoginData : Map<String,String> = mapOf(
        Pair("Ivo","test1234"),
        Pair("",""),
        //Pair("",""),
        //Pair("",""),
        //Pair("",""),
        //Pair("",""),


    )
    override fun checkLogin(email: String, password: String): Boolean {
        return testLoginData[email] == password
    }
}