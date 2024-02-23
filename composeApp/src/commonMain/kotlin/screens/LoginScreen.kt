package screens

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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class LoginScreen(
    val appViewModel: AppViewModel

) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        //val screenModel = navigator.rememberNavigatorScreenModel<LoginScreenModel>()


        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
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
                        onValueChange = { username = it },
                        value = username,
                        label = { Text(text = "Username") })
                    Spacer(Modifier.height(10.dp))
                    TextField(
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { password = it },
                        value = password,
                        label = { Text(text = "Password") })
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = "forgot Password?",
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
        navigator.push(HomeScreen(appViewModel = appViewModel))
    }
}