package screens.game_manager

import AppViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Match
import screens.ChooseMatchScreen

val containerColor: Color = Color.Blue.copy(alpha = 0.3f)

class GameManager(
    val appViewModel: AppViewModel
) : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerTopCenter()
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                TeamArea(
                    label = "Heim",
                    teamName = appViewModel.currentMatch?.team1?.name?:"Error",
                    onGoalButtonClick = { },
                    onPenaltyButtonClick = { println("Heimteam Strafe Button funktionert") },
                    onTimeoutButtonClick = { println("Heimteam Timeout Button funktionert") }
                )
                ControlArea(navigator = navigator)
                TeamArea(
                    label = "Gast",
                    teamName = appViewModel.currentMatch?.team2?.name?:"Error",
                    onGoalButtonClick = { },
                    onPenaltyButtonClick = { println("Gastteam Strafe Button funktionert") },
                    onTimeoutButtonClick = { println("Gastteam Timeout Button funktionert") }
                )
            }
        }
    }

    @Composable
    private fun RowScope.ControlArea(navigator: Navigator) {
        var periodCounter by remember { mutableStateOf(1) }
        var timerRunning by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                // MinusSign
                Button(
                    enabled = periodCounter > 1,
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    onClick = { periodCounter-- },
                    shape = RoundedCornerShape(CornerSize(15.dp))
                ) {
                    Text("-", fontSize = 30.sp)
                }

                // PeriodeDisplay
                Box(
                    modifier = Modifier.background(
                        color = containerColor,
                        shape = RoundedCornerShape(corner = CornerSize(15.dp))

                    )
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "$periodCounter", fontSize = 30.sp)
                }

                // PlusSign
                Button(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    onClick = { periodCounter++ },
                    shape = RoundedCornerShape(CornerSize(15.dp))
                ) {
                    Text("+", fontSize = 30.sp)
                }

            }
            //TODO PlayButton

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.height(63.dp),
                    shape = CircleShape,
                    onClick = {
                        timerRunning = !timerRunning
                    }
                ) {
                    if (timerRunning) {
                        Text("||", fontWeight = FontWeight(1000), fontSize = 20.sp)
                    } else {
                        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                    }
                }

                // TODO Reset Button with Link to Startpage
                Button(
                    modifier = Modifier.height(63.dp),
                    shape = CircleShape,
                    onClick = {
                        navigator.push(ChooseMatchScreen(appViewModel = appViewModel))                     }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            }


        }
    }

    @Composable
    private fun TimerTopCenter() {
        Box(
            modifier = Modifier.fillMaxWidth(0.5f).height(100.dp)
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 50.sp,
                text = "20:00",
            )
        }
    }

    @Composable
    private fun RowScope.TeamArea(
        label: String,
        teamName: String,
        onGoalButtonClick: () -> Unit,
        onPenaltyButtonClick: () -> Unit,
        onTimeoutButtonClick: () -> Unit,
    ) {
        var timeoutAvailable by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.weight(1.25f).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = label)

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(60.dp)
                    .background(color = containerColor, shape = RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = teamName,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 5.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var goalCounter by remember { mutableStateOf(0) }

                // Button (Goal)
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        onGoalButtonClick()
                        goalCounter++
                    }
                ) {
                    Text(text = "Goal")
                }

                // Goalanzeige
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = containerColor,
                            shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "$goalCounter", fontSize = 15.sp)
                }
            }

            // Button (Strafe)
            Button(
                modifier = Modifier.width(120.dp),
                onClick = { onPenaltyButtonClick() }
            ) {
                Text(text = "Strafe")
            }

            // TODO Strafanzeige als Kreis (Kreis ist ausgefüllt falls Strafe läuft)
            //...

            // Button (Timeout)
            Button(
                enabled = timeoutAvailable,
                modifier = Modifier.width(120.dp),
                onClick = {
                    timeoutAvailable = false
                    onTimeoutButtonClick()
                }
            ) {
                Text(text = "Timeout")
            }

            // TODO Strafanzeige als Kreis (Kreis ist ausgefüllt falls Strafe läuft)
            //...


        }
    }

}