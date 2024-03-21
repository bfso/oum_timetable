package screens

import ui_components.DropdownMenuPlayers
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
import androidx.compose.material.TextField
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
import data.Match
import data.Player
import ui_components.ScreenWithKeyInput

class FoulScreen(
    val team: Team,
    val match: Match
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var faulChosen by remember { mutableStateOf(false) }
        var dropDownValueFaul: Player? by remember { mutableStateOf(null) }
        var showAlertBox by remember { mutableStateOf(false) }
        var penaltyTime by remember { mutableStateOf("") }
        val test = ""


        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Escape) { navigator.pop() }
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (showAlertBox) {
                    AlertDialog(
                        onDismissRequest = { showAlertBox = false },
                        buttons = {
                            Box(
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
                        title = {
                            Text(
                                text = "Error",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        text = {
                            Text(
                                text = "Choose a faul maker and how long the penalty lasts",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        contentColor = Color.Red,
                        modifier = Modifier.size(width = 300.dp, height = 200.dp),
                        backgroundColor = Color.Gray
                    )
                }

                Column(modifier = Modifier.align(alignment = Alignment.Center)) {
                    Box() {
                        Text(text = "${team.name}", fontSize = 30.sp)
                    }
                    Box() {
                        DropdownMenuPlayers(
                            modifier = Modifier.height(56.dp),
                            players = team.members,
                            value = if (dropDownValueFaul == null) {
                                "Choose who made Faul"
                            } else {
                                "${dropDownValueFaul!!.firstName} ${dropDownValueFaul!!.name}, Number: ${dropDownValueFaul!!.playerNumber}"
                            },
                            label = "Faul",
                            onItemClick = { match ->
                                dropDownValueFaul = match
                                faulChosen = true
                            },
                        )


                    }

                    Box() {
                        TextField(
                            singleLine = true,
                            onValueChange = { value ->
                                penaltyTime = value.filter { it.isDigit() }
                            }, //penaltyTime = it
                            value = penaltyTime,
                            label = { Text(text = "Faul Time") })
                    }
                    Box() {
                        Button(
                            onClick = {
                                if (faulChosen && !penaltyTime.equals(test)) {
                                    navigator.push((Playmanager(match)))
                                } else {
                                    showAlertBox = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (faulChosen && !penaltyTime.equals(test)) {
                                    Color.Green
                                } else {
                                    Color.Red
                                }
                            )
                        ) {

                            Text(text = "Send")
                        }
                    }
                }
            }
        }
    }

    public fun doSomthin() {

    }
}