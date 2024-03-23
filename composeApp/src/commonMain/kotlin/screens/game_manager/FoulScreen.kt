package screens.game_manager

import AppViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
    val alertBox = AlertBox("Choose a foul maker and how long the penalty should last")

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var player: Player? by remember { mutableStateOf(null) }
        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Escape) { navigator.pop() }
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            alertBox()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(text = team.name, fontSize = 30.sp)
                    GenericDropdownMenu(
                        modifier = Modifier.height(56.dp).fillMaxWidth(),
                        iterable = team.members,
                        value = if (player == null) {
                            "Choose who made a Foul"
                        } else {
                            "${player!!.firstName} ${player!!.name}, Number: ${player!!.playerNumber}"
                        },
                        label = "Player",
                        toString = { "${it.playerNumber} ${it.firstName} ${it.name}" },
                        onItemClick = { player = it },
                    )
                    IncrementerWithDisplay(
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        minIndex = 0,
                        decrementSymbol = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        incrementSymbol = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        maxIndex = foulTimes.lastIndex,
                        display = { "${foulTimes[it]} min" }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                            alignment = Alignment.End
                        )
                    ) {
                        Button(
                            onClick = {
                                if (player == null) {
                                    alertBox.show()
                                } else {
                                    navigator.pop()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (player == null) {
                                    Color.Red
                                } else {
                                    Color.Green
                                }
                            )
                        ) {
                            Text(text = "confirm")
                        }
                        Button(
                            onClick = {
                                navigator.pop()
                            }
                        ) {
                            Text(text = "cancel")
                        }
                    }


                }
            }
        }
    }
}


