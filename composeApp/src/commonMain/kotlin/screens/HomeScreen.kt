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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Match

class HomeScreen(val appViewModel: AppViewModel) :Screen{
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Box(modifier = Modifier){
                Column (){
                    Text(text = "Test")
                    Button(
                        onClick = {navigator.push(ChooseTeamScreen(match = Match()))}
                    ){
                        Text(text = "Anwesenheitscheck")
                    }
                }
            }
        }


    }

}