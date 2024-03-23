package screens

import AppViewModel
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
import androidx.compose.ui.input.key.Key
import ui_components.AlertBox
import ui_components.GenericDropdownMenu

class ChooseMatchScreen(
    val appViewModel: AppViewModel
) : Screen {
    val alertBox = AlertBox("Pick a Match")
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        alertBox()


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                var dropDownValue: Match? by remember { mutableStateOf(null) }
                GenericDropdownMenu(
                    modifier = Modifier.height(56.dp),
                    iterable = appViewModel.matches.filter { !it.finished },
                    value = if (appViewModel.currentMatch == null) {
                        "Choose a Match"
                    } else {
                        "${appViewModel.currentMatch!!.team1.name} vs. ${appViewModel.currentMatch!!.team2.name}"
                    },
                    label = "Match",
                    toString = { "${it.team1.name} vs. ${it.team2.name}" },
                    onItemClick = { match ->
                        dropDownValue = match
                        appViewModel.currentMatch = dropDownValue
                    }
                )
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (appViewModel.currentMatch != null) Color.Green else Color.Red),
                    onClick = {
                        if (appViewModel.currentMatch != null) {

                            navigator.push(ChooseTeamScreen(appViewModel))
                        } else {
                            alertBox.show()
                        }
                    }) {
                    Text(text = "Anwesenheitscheck")
                }
            }
        }
    }
}


