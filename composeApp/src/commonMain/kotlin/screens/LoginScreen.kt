package screens

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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class LoginScreen:Screen {
    @Composable
    override fun Content() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { username = it },
                        value = username,
                        label = { Text(text = "Username") })
                    Spacer(Modifier.height(10.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { password = it },
                        value = password,
                        label = { Text(text = "Password") })
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                        Text(
                            text = "forgot Password?",
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier /*.onClick { TODO()} */
                        )
                    }
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        Button(
                            onClick = { TODO() },
                        ) {
                            Text(text = "Login")
                        }
                    }
                }
            }
        }
    }
}