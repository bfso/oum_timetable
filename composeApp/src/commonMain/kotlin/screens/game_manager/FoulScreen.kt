package screens.game_manager

import AppViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Player
import pre_sets.foulTimes
import ui_components.AlertBox
import ui_components.GenericDropdownMenu
import ui_components.IncrementerWithDisplay
import ui_components.ScreenWithKeyInput

class FoulScreen(
    val team: Team,
    val appViewModel: AppViewModel
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var playerChosen by remember { mutableStateOf(false) }
        var dropDownValueFaul: Player? by remember { mutableStateOf(null) }
        var showAlertBox by remember { mutableStateOf(false) }


        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Escape) { navigator.pop() }
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                ) {

                AlertBox(
                    showAlertBox = showAlertBox,
                    errorMessage = "Choose a faul maker and how long the penalty lasts"
                ) {
                    showAlertBox = false
                }
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(text = team.name, fontSize = 30.sp)
                    GenericDropdownMenu(
                        modifier = Modifier.height(56.dp).fillMaxWidth(),
                        iterable = team.members,
                        value = if (dropDownValueFaul == null) {
                            "Choose who made a Faul"
                        } else {
                            "${dropDownValueFaul!!.firstName} ${dropDownValueFaul!!.name}, Number: ${dropDownValueFaul!!.playerNumber}"
                        },
                        label = "Faul",
                        toString = { "${it.playerNumber} ${it.firstName} ${it.name}" },
                        onItemClick = { match ->
                            dropDownValueFaul = match
                            playerChosen = true
                        },
                    )
                    IncrementerWithDisplay(
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        minIndex = 0,
                        decrementSymbol = { Text(text="<", fontSize = 40.sp) },
                        incrementSymbol = { Text(text = ">", fontSize = 40.sp) },
                        maxIndex = foulTimes.lastIndex,
                        display = { foulTimes[it].toString()}
                    )
                    //TextField(
                    //    singleLine = true,
                    //    onValueChange = { value ->
                    //        penaltyTime = value.filter { it.isDigit() }
                    //    }, //penaltyTime = it
                    //    value = penaltyTime,
                    //    label = { Text(text = "Faul Time") })


                    Button(
                        onClick = {
                            if (playerChosen) {
                                navigator.push((GameManager(appViewModel = appViewModel)))
                            } else {
                                showAlertBox = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (playerChosen) {
                                Color.Green
                            } else {
                                Color.Red
                            }
                        )
                    ) {
                        Text(text = "confirm")
                    }
                }
            }
        }
    }


}