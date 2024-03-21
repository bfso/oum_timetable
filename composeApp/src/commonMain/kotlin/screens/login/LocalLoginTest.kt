package screens.login

object LocalLoginTest: Login {

    private val testLoginData : Map<String,String> = mapOf(
        Pair("Ivo","test1234"),
        Pair("",""),
        //Pair("",""),
        //Pair("",""),
        //Pair("",""),
        //Pair("",""),


    )
    override fun login(username: String, password: String): Boolean {
        return testLoginData[username] == password
    }
}