package screens.game_manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import timer.Timer
import timer.minutes
import timer.seconds
import ui_components.IncrementerWithDisplay


class GameManager(
    val currentMatch: Match
) : Screen {

    //var timerRunning by mutableStateOf(false)
    val timer: Timer = Timer(durationMillis = 20.minutes, onTimerFinish = { })
    val team1Timer: Timer = Timer(format = "ss:SS",durationMillis = 30.seconds, onTimerFinish = { timeoutBlocked = false })
    val team2Timer: Timer = Timer(format = "ss:SS",durationMillis = 30.seconds, onTimerFinish = { timeoutBlocked = false })

    var team1TimeoutAvailable by mutableStateOf(true)
    var team2TimeoutAvailable by mutableStateOf( true)
    var timeoutBlocked by mutableStateOf( false)



    companion object {
        val containerColor: Color = Color.Blue.copy(alpha = 0.3f)
        val cornerRadius = 15.dp
    }


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            TimerTopCenter()
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                TeamArea(
                    label = "Heim",
                    teamName = currentMatch.team1.name,
                    onGoalButtonClick = {

                    },
                    onPenaltyButtonClick = {
                        navigator.push(
                            FoulScreen(team = currentMatch.team1)
                        )
                    },
                    onTimeoutButtonClick = {
                        if(!timeoutBlocked&&timer.isRunning){
                            team1TimeoutAvailable = false
                            team1Timer.start()
                            timer.pause()
                            timeoutBlocked = true
                        }

                    },
                    timeoutTimer = team1Timer,
                    timeoutAvailable = team1TimeoutAvailable
                )
                ControlArea(navigator = navigator)
                TeamArea(
                    label = "Gast",
                    teamName = currentMatch.team2.name,
                    onGoalButtonClick = {

                    },
                    onPenaltyButtonClick = {
                        navigator.push(
                            FoulScreen(team = currentMatch.team2)
                        )
                    },
                    onTimeoutButtonClick = {
                        if(!timeoutBlocked&&timer.isRunning){
                            team2TimeoutAvailable = false
                            team2Timer.start()
                            timer.pause()
                            timeoutBlocked = true
                        }
                    },
                    timeoutTimer = team2Timer,
                    timeoutAvailable = team2TimeoutAvailable
                )
            }
        }
    }

    @Composable
    private fun RowScope.ControlArea(navigator: Navigator) {
        var periodCounter by remember { mutableStateOf(1) }
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            IncrementerWithDisplay(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                minIndex = 1,
                startingIndex = 1,
                readIndex = { periodCounter = it }
            )
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
                    enabled = !timeoutBlocked,
                    modifier = Modifier.height(63.dp),
                    shape = CircleShape,
                    onClick = {
                        timer.toggleOnOff()
                    }
                ) {
                    if (timer.isRunning) {
                        Text("||", fontWeight = FontWeight(1000), fontSize = 20.sp)
                    } else {
                        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
                    }
                }

                // Reset Button with Link to Startpage
                Button(
                    modifier = Modifier.height(63.dp),
                    shape = CircleShape,
                    onClick = {
                        currentMatch.finished = true
                        navigator.push(ChooseMatchScreen())
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            }


        }
    }

    @Composable
    private fun TimerTopCenter() {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
                .background(
                    color = containerColor,
                    shape = RoundedCornerShape(cornerRadius)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 50.sp,
                text = timer.formattedTime,
            )
        }
    }

    @Composable
    private fun RowScope.TeamArea(
        label: String = "",
        teamName: String = "",
        onGoalButtonClick: () -> Unit = {},
        onPenaltyButtonClick: () -> Unit = {},
        onTimeoutButtonClick: () -> Unit = {},
        timeoutAvailable: Boolean,
        timeoutTimer: Timer
    ) {
        //var  timeoutAvailable: Boolean by  remember { mutableStateOf(true)}
        //val  timeoutTimer: Timer by remember { mutableStateOf(Timer(format = "ss:SS", durationMillis = 30.seconds))}
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
                    .background(color = containerColor, shape = RoundedCornerShape(cornerRadius)),
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
                            shape = RoundedCornerShape(cornerRadius)
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

            // Button (Timeout)
            Button(
                enabled = timeoutAvailable,
                modifier = Modifier.width(120.dp),
                onClick = {
                    onTimeoutButtonClick()
                    //timeoutTimer.start()
                    //timeoutAvailable = false
                }
            ) {
                Text(text = "Timeout")
            }
            if (timeoutTimer.isRunning) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(
                            color = containerColor,
                            shape = RoundedCornerShape(cornerRadius)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = timeoutTimer.formattedTime, fontSize = 30.sp)
                }

            }


            // TODO Strafanzeige als Kreis (Kreis ist ausgefüllt falls Strafe läuft)
            //...


        }
    }

}

