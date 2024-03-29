package screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import screens.ChooseMatchScreen
import ui_components.AlertBox


class LoginScreenModel :ScreenModel {

    var username by   mutableStateOf("")
    var password by   mutableStateOf("")
    var passwordVisible by   mutableStateOf(false)
    private val loginService: LoginService = LocalLoginTest
    val alertBox: AlertBox = AlertBox("Email or password are incorrect")
    lateinit var navigator: Navigator

    init {
        println("Login Screen model is initializing")
        screenModelScope.launch {
            delay(2000)

        }
    }

    override fun onDispose() {
        super.onDispose()
        println("Login Screen model is disposing")

    }

    fun validateLoginData() :Boolean{
        val loginDataValid = loginService.checkLogin(username = username, password =  password)
        if(loginDataValid){
            navigator.push(ChooseMatchScreen())
        }else{
                alertBox.show()
        }
        return loginDataValid
    }

    fun goToForgotLoginScreen() {
        navigator.push(ForgotPasswordScreen())
    }
}
