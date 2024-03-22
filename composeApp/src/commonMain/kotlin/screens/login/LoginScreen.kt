package screens.login

import AppViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import screens.ChooseMatchScreen
import ui_components.ScreenWithKeyInput

class LoginScreen(
    val appViewModel: AppViewModel,
) : Screen {
    val login: Login = LocalLoginTest



    override val key: ScreenKey = uniqueScreenKey

    val loginScreenModel by mutableStateOf(LoginScreenModel())
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        //val loginScreenModel = navigator.rememberNavigatorScreenModel<LoginScreenModel>()






        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Enter){
                    try {
                        validateLoginData(navigator)
                        true
                    }catch (_:Exception){
                        false
                    }
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                        Text(text = "Login", fontSize = 30.sp)
                    }
                    Spacer(Modifier.height(20.dp))
                    TextField(
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { loginScreenModel.username = it },
                        value = loginScreenModel.username,
                        label = { Text(text = "Username") })
                    Spacer(Modifier.height(10.dp))
                    TextField(
                        visualTransformation = if (loginScreenModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { loginScreenModel.password = it },
                        trailingIcon = {
                            //TODO find a better icon that works on both platforms
                            val image = if (loginScreenModel.passwordVisible)
                                Icons.Filled.Lock
                            else Icons.Filled.Lock

                            // Please provide localized description for accessibility services
                            val description = if (loginScreenModel.passwordVisible) "Hide password" else "Show password"

                            IconButton(onClick = {loginScreenModel.passwordVisible = !loginScreenModel.passwordVisible}){
                                Icon(imageVector  = image, description)
                            }
                        },
                        value = loginScreenModel.password,
                        label = { Text(text = "Password") })
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = "Forgot password?",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .pointerHoverIcon(
                                    icon = PointerIcon.Hand,
                                    overrideDescendants = true
                                )
                                .clickable{
                                    navigator.push(ForgotPasswordScreen())
                                }
                        )
                    }
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Button(
                            modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
                            onClick = {
                                try {
                                    validateLoginData(navigator)
                                } catch (_: Exception) {

                                }
                            },
                        ) {
                            Text(text = "Login")
                        }
                    }
                }
            }
        }
    }

    private fun validateLoginData(navigator:Navigator) {
        if(login.login(username = loginScreenModel.username, password =  loginScreenModel.password)){
            navigator.push(ChooseMatchScreen(appViewModel = appViewModel))
        }
    }
}