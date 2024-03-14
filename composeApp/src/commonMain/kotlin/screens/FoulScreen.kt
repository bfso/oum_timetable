package screens

import DropdownMenuPlayers
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import data.Team
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Player

class FoulScreen(
    val team: Team
) : Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var goalChosen by remember { mutableStateOf( false) }
        var assistChosen by remember { mutableStateOf( false) }
        var dropDownValueGoal:Player? by remember { mutableStateOf( null) }
        var dropDownValueAssist:Player? by remember { mutableStateOf( null) }
        var showAlertBox by remember { mutableStateOf( false) }

        ScreenWithKeyInput (
            keyEvents = mapOf(
                Pair(Key.Escape){navigator.pop()}
            ),
            modifier = Modifier.fillMaxSize()
        ){
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
            }
        }

        Box( modifier = Modifier.fillMaxSize()) {
            if (showAlertBox){
                AlertDialog(
                    onDismissRequest = { showAlertBox = false},
                    buttons = {
                        Box (
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = {
                                    showAlertBox = false
                                }
                            ) {
                                Text(text = "Close")
                            }
                        }
                    },
                    title = { Text(text = "Error", modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Center)  },
                    text = { Text(text = "Choose a goal shooter and an assist maker", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                    contentColor = Color.Red,
                    modifier = Modifier.size(width = 300.dp, height = 200.dp),
                    backgroundColor = Color.Gray
                )
            }

            Column (modifier = Modifier.align(alignment = Alignment.Center)) {
                Box() {
                Text(text = "${team.name}", fontSize = 30.sp)
                }
                Box() {
                DropdownMenuPlayers(
                    modifier = Modifier.height(56.dp),
                    players = team.members,
                    value = if(dropDownValueGoal == null){
                        "Choose who made goal"
                    }else{
                        "${dropDownValueGoal!!.firstName} vs. ${dropDownValueGoal!!.name}"
                    },
                    label = "Goal",
                    onItemClick = {
                            match ->
                        dropDownValueGoal = match
                        goalChosen = true
                    },
                    )


                        }

                Box(){

                }
                Box(){
                    Button( onClick = {
                        if (goalChosen && assistChosen){
                            //navigator.push((Playermanager(appViewModel = appViewModel)))
                        } else {
                            showAlertBox = true
                        } }, colors = ButtonDefaults.buttonColors(backgroundColor = if (goalChosen && assistChosen){ Color.Green} else {Color.Red})){

                        Text(text = "Send")
                    }
                }
            }
        }
    }

    public fun doSomthin(){

    }
}