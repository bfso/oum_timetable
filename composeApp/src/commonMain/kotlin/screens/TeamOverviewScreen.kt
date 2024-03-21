package screens

import AppViewModel
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.Player
import ui_components.ScreenWithKeyInput

class TeamOverviewScreen(
    val appViewModel: AppViewModel,
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
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(appViewModel.currentMatch!!.team1.members) {
                        PlayerCard(player = it)
                    }
                }

            }

        }
    }

    @Composable
    fun PlayerCard(player: Player = Player()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.Center,
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
                    modifier = Modifier

                ) {
                    Column(

                    ) {
                        Text(text = "Name: ")
                        Text(text = "Vorname: ")
                        Text(text = "Spielernummer: ")
                        Text(text = "Lizenznummer: ")
                        Text(text = "Anwesend: ")
                    }
                    Column(

                    ) {
                        Text(text = player.name)
                        Text(text = player.firstName)
                        Text(text = player.playerNumber.toString())
                        Text(text = player.licenceNumber.toString())
                        //DropdownMenu(
                        //    options = listOf(),
                        //    onChange = {}
                        //)
                    }
                    Image(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                }
            }
        }
    }
}