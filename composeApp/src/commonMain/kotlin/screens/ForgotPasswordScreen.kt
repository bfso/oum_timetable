package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ui_components.ScreenWithKeyInput

class ForgotPasswordScreen : Screen {



    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var test by remember { mutableStateOf("No Button Pressed") }
        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Escape) { navigator.pop() },
                Pair(Key.Spacebar){
                    test = "Spacebar Pressed"
                    true}
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(text = "Forgot Password", fontSize = 50.sp)
                Button(
                    modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
                    onClick = {
                    navigator.pop()
                }) {
                    Text(text = "Back")
                }
            }
        }
    }




}