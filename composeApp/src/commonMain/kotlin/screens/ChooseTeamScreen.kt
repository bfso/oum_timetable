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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import screens.check_player_attendance.CheckPlayerAttendanceScreen
import screens.game_manager.GameManager
import ui_components.ScreenWithKeyInput

class ChooseTeamScreen (private val appViewModel: AppViewModel):Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showAlertBox = false
        Box(

            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.spacedBy(40.dp),

            ) {
                if (showAlertBox){
                    AlertDialog(
                        onDismissRequest = { showAlertBox = false},
                        buttons = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Button(
                                    onClick = {
                                        showAlertBox = false
                                    }
                                ) {
                                    Text(text = "Close")
                                }
                            }
                        },
                        title = { Text(text = "Error",  modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Center) },
                        text = { Text(text = "1. Check if you picked a team 2. Check if attendance check is done for both teams",  modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Center)},
                        contentColor = Color.Red,
                        modifier = Modifier.size(width = 300.dp, height = 200.dp),
                        backgroundColor = Color.Gray
                    )
                }

                Button(
                    enabled = !appViewModel.team1Ready,
                    onClick = {
                              navigator.push(CheckPlayerAttendanceScreen(appViewModel = appViewModel, confirmTeamChecked =  {
                                  //TODO Somehow this schould change the boolean in the viewmodel when the button on the next screen is pressed
                                  appViewModel.team1Ready = true
                              }))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .pointerHoverIcon(icon = PointerIcon.Hand),
                ){
                    Text(text = appViewModel.currentMatch!!.team1.name)
                }
                Button(
                    enabled = !appViewModel.team2Ready,
                    onClick = {
                        navigator.push(CheckPlayerAttendanceScreen(appViewModel = appViewModel, confirmTeamChecked =  {
                            appViewModel.team2Ready = true
                        }))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .pointerHoverIcon(icon = PointerIcon.Hand)
                ){
                    Text(text = appViewModel.currentMatch!!.team2.name)
                }

                //Button(
                //    onClick = {navigator.pop()}
                //){
                //    Text(text = "go back")
                //}
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (appViewModel.team1Ready && appViewModel.team2Ready) Color.Green else Color.Red),
                    onClick = {
                        if (appViewModel.team1Ready && appViewModel.team2Ready){
                            navigator.push((GameManager(appViewModel)))
                        } else {
                            showAlertBox = true
                        }

                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Start Match")
                }
            }
        }
    }
}