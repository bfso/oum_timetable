package screens

import AppViewModel
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
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Match
import data.Player
import pre_sets.FoulTimes
import pre_sets.foulTimes
import screens.check_player_attendance.CheckPlayerAttendanceScreen
import screens.game_manager.GameManager
import ui_components.AlertBox
import ui_components.ScreenWithKeyInput

class ChooseTeamScreen(val currentMatch: Match):Screen {

    val alertBox = AlertBox(errorMessage = "Please check the attendance of both teams first")

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        alertBox()
        Box(

            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                Button(
                    enabled = !currentMatch.team1Ready,
                    onClick = {
                              navigator.push(CheckPlayerAttendanceScreen(currentMatch.team1 , confirmTeamChecked =  {
                                  currentMatch.team1Ready = true
                              }))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .pointerHoverIcon(icon = PointerIcon.Hand),
                ){
                    Text(text = currentMatch.team1.name)
                }
                Button(
                    enabled = !currentMatch.team2Ready,
                    onClick = {
                        navigator.push(CheckPlayerAttendanceScreen(currentMatch.team2, confirmTeamChecked =  {
                            currentMatch.team2Ready = true
                        }))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .pointerHoverIcon(icon = PointerIcon.Hand)
                ){
                    Text(text = currentMatch.team2.name)
                }

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (currentMatch.team1Ready && currentMatch.team2Ready) Color.Green else Color.Red),
                    onClick = {
                        if (currentMatch.team1Ready && currentMatch.team2Ready){
                            navigator.push((GameManager(currentMatch, player = Player())))
                        } else {
                            alertBox.show()
                        }

                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Start Match")
                }
            }
        }
    }
}