package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import data.Match


class Playmanager(
    val match: Match
) : Screen {
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Time (center)
            Box(
                modifier = Modifier.fillMaxWidth(0.5f).height(100.dp).background(Color.Blue.copy(alpha = 0.5f )),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    fontSize = 50.sp,
                    text = "20:00",
                )
            }


            // Timeeditbutton (right)

            // Hometeam (left)


            Box(Modifier.fillMaxSize()) {
                Box(
                    Modifier.align(Alignment.CenterStart).width(200.dp).height(40.dp)
                        .background(Color.Red)
                )
                Text(match.team1.name, modifier = Modifier.align(Alignment.CenterStart))
            }

            // Period (center, with + and - for adding or deleting a Period)

            // Guestteam (right)
            Box(Modifier.fillMaxSize()) {
                Box(
                    Modifier.align(Alignment.CenterEnd).width(200.dp).height(40.dp)
                        .background(Color.Green)
                )
                Text(match.team2.name, modifier = Modifier.align(Alignment.CenterEnd))
            }

            // Goalbutton for Hometeam (left)
            Button(
                onClick = {},
                modifier = Modifier,
            ) {
                Text(text = "Goal hinzufügen")
            }
        }

        // Pausebutton

        // Goalbutton for Guestteam (right)

        // Penaltybutton for Hometeam (left)

        // Penaltybutton for Guestteam (right)

        // Timeoutbutton for Hometeam (left)

        // Timeoutbutton for Guestteam (right)
    }
}

