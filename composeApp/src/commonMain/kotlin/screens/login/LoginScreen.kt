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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
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
import cafe.adriel.voyager.core.model.rememberNavigatorScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.DataTest
import screens.ChooseMatchScreen
import ui_components.AlertBox
import ui_components.ScreenWithKeyInput

class LoginScreen(
    //val appViewModel: AppViewModel,
) : Screen {
    override val key: ScreenKey = uniqueScreenKey
    private lateinit var loginScreenModel:LoginScreenModel



    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        loginScreenModel = rememberScreenModel { LoginScreenModel() }
        loginScreenModel.navigator = LocalNavigator.currentOrThrow
        loginScreenModel.navigator.rememberNavigatorScreenModel { AppViewModel(DataTest()) }

        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Enter){
                    loginScreenModel.validateLoginData()
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            loginScreenModel.alertBox()
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
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff
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
                                    loginScreenModel.goToForgotLoginScreen()
                                }
                        )
                    }
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Button(
                            modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
                            onClick = {
                                    loginScreenModel.validateLoginData()
                            },
                        ) {
                            Text(text = "Login")
                        }
                    }
                }
            }
        }
    }

}