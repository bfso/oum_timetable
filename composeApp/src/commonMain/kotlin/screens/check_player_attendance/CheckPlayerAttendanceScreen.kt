package screens.check_player_attendance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Player
import data.Team
import enums.Attendance
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import oum_timetable.composeapp.generated.resources.Res
import oum_timetable.composeapp.generated.resources.profile_avatar_missing
import ui_components.GenericDropdownMenu
import ui_components.ScreenWithKeyInput
import ui_components.TextDisplayWithLabel

class CheckPlayerAttendanceScreen(
    private val team: Team,
    private val confirmTeamChecked: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ScreenWithKeyInput(
            keyEvents = mapOf(
                Pair(Key.Escape) { navigator.pop() }
            ),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Button(onClick = {
                        confirmTeamChecked()
                        navigator.pop()

                    }) {
                        Text(text = "finished")
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(
                        minSize = 380.dp
                    ),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(team.members) {
                        PlayerCard(player = it)
                    }
                }

            }

        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun PlayerCard(player: Player = Player()) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize(0.9f)
                    .border(
                        BorderStroke(
                            width = 2.dp,
                            color = Color.Black
                        )
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Column(

                    ) {
                        TextDisplayWithLabel(
                            modifier = Modifier.widthIn(max = 200.dp),
                            value = player.name,
                            label = "Name"
                        )
                        TextDisplayWithLabel(
                            modifier = Modifier.widthIn(max = 200.dp),
                            value = player.firstName,
                            label = "Vorname"
                        )
                        TextDisplayWithLabel(
                            modifier = Modifier.widthIn(max = 200.dp),
                            value = player.playerNumber.toString(),
                            label = "Spielernummer"
                        )
                        TextDisplayWithLabel(
                            modifier = Modifier.widthIn(max = 200.dp),
                            value = player.licenceNumber.toString(),
                            label = "Lizenznummer"
                        )
                    }

                    if (player.image == null){
                         Image(painter = painterResource(Res.drawable.profile_avatar_missing), contentDescription = "")
                    }else{
                        Image(imageVector = player.image,contentDescription = "Image of ${player.firstName} ${player.name}")
                    }
                }
                var value:Attendance? by remember{ mutableStateOf(null) }
                    GenericDropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        iterable = Attendance.entries,
                        label = "Anweseneheit:",
                        startingValue = "choose",
                        onItemClick = {
                                      value = it
                            //TODO
                            //where should this attendance info be stored?
                            //It is specific to a player in a team during a specific match
                        },
                        toString = {it.string},
                        textColor = when(value){
                            Attendance.ABSENT-> Color.Red
                            Attendance.PRESENT->Color.Green
                            else -> null
                        },
                        textStyle = TextStyle(
                            fontWeight = FontWeight(500)
                        )

                    )
            }
        }
    }
}