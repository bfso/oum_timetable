package screens

import AppViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Match

class HomeScreen(val appViewModel: AppViewModel) :Screen{
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Enter){navigator.push(ChooseTeamScreen(match = Match()));true},
                Pair(Key.Escape){navigator.pop()},

                )
        ){

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Column (){
                    Text(text = "Test")
                    Button(
                        modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
                        onClick = {navigator.push(ChooseTeamScreen(match = Match()))}
                    ){
                        Text(text = "Anwesenheitscheck")
                    }
                }
        }

        }

    }

}