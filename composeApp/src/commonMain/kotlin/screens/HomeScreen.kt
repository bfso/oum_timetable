package screens

import AppViewModel
import DropdownMenuCustom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Match
import data.Team

class HomeScreen (
    val appViewModel: AppViewModel
): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showAlertBox by remember { mutableStateOf(false) }
        var showAlertBoxMatch by remember { mutableStateOf( false ) }
        var teamCheckDone by remember { mutableStateOf( false ) }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier) {
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
                        text = { Text(text = "Choose a Match", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                        contentColor = Color.Red,
                        modifier = Modifier.size(width = 300.dp, height = 200.dp),
                        backgroundColor = Color.Gray
                    )
                }

                if (showAlertBoxMatch){
                    AlertDialog(
                        onDismissRequest = { showAlertBoxMatch = false},
                        buttons = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Button(
                                    onClick = {
                                        showAlertBoxMatch = false
                                    }
                                ) {
                                    Text(text = "Close")
                                }
                            }
                        },
                        title = { Text(text = "Error") },
                        text = { Text(text = "First look that you chose a Match and have checked the Attendance check")},
                        contentColor = Color.Red,
                        modifier = Modifier.size(width = 300.dp, height = 200.dp),
                        backgroundColor = Color.Gray
                    )
                }




                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)

                ) {
                    var dropDownValue:Match? by remember { mutableStateOf(null)}
                    var bothTeamsChecked by remember { mutableStateOf(false)}

                    DropdownMenuCustom(
                        modifier = Modifier.height(56.dp),
                        matches = listOf(
                            Match(team1 = Team(name = "Team 1"), team2 =  Team(name = "Team 2")),
                            Match(team1 = Team(name = "Team 3"), team2 = Team(name = "Team 4")),
                            Match(team1 = Team(name = "Team 5"), team2 = Team(name = "Team 6")),
                            Match(team1 = Team(name = "Team 1"), team2 = Team(name = "Team 3")),
                            Match(team1 = Team(name = "Team 2"), team2 = Team(name = "Team 3")),

                            ),
                        value = if(dropDownValue == null){
                            "Choose Match"
                        }else{
                            "${dropDownValue!!.team1.name} vs. ${dropDownValue!!.team2.name}"
                        },
                        label = "Match",
                        onItemClick = {
                                match ->
                            dropDownValue = match
                        }
                    )


                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (dropDownValue!=null) Color.Green else Color.Red),
                        onClick = {
                            if(dropDownValue!=null){
                                teamCheckDone = true
                                navigator.push(ChooseTeamScreen(dropDownValue!!))
                            } else {
                                showAlertBox = true
                            }
                        }){
                        Text(text = "Anwesenheitscheck")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (bothTeamsChecked) Color.Green else Color.Red),
                        onClick = {
                            if (teamCheckDone){
                                //navigator.push((Playermanager(appViewModel = appViewModel)))
                            } else {
                                showAlertBoxMatch = true
                            }

                        }) {
                            Text(text = "Start Match")
                    }
                }
            }
        }
    }
}