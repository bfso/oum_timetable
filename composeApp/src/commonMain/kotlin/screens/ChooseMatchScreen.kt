package screens

import AppViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import ui_components.AlertBox
import ui_components.GenericDropdownMenu

class ChooseMatchScreen: Screen {
    val alertBox = AlertBox("Pick a Match")
    private lateinit var appViewModel: AppViewModel
    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        appViewModel = navigator.getNavigatorScreenModel()
        alertBox()


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                GenericDropdownMenu(
                    modifier = Modifier.height(56.dp),
                    iterable = appViewModel.matches.filter { !it.finished },
                    startingValue = if (appViewModel.currentMatch == null) {
                        "Choose a Match"
                    } else {
                        "${appViewModel.currentMatch!!.team1.name} vs. ${appViewModel.currentMatch!!.team2.name}"
                    },
                    label = "Match",
                    toString = { "${it.team1.name} vs. ${it.team2.name}" },
                    onItemClick = { match ->
                        appViewModel.currentMatch = match
                    }
                )
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (appViewModel.currentMatch != null) Color.Green else Color.Red),
                    onClick = {
                        if (appViewModel.currentMatch != null) {
                            navigator.push(ChooseTeamScreen(currentMatch = appViewModel.currentMatch!!))
                            appViewModel.currentMatch = null
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


