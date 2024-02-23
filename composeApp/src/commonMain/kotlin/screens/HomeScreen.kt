package screens

import DropdownMenuCustom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import data.Match
import data.Team
import javax.swing.SpinnerListModel

class HomeScreen : Screen {
    @Composable
    override fun Content() {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)

                ) {
                    var dropDownValue:Match? by remember { mutableStateOf(null)}
                    var bothTeamsChecked by remember { mutableStateOf(false)}

                    DropdownMenuCustom(
                        modifier = Modifier.height(56.dp),
                        matches = listOf(
                            Match(Team(name = "Team 1"), Team(name = "Team 2")),
                            Match(Team(name = "Team 3"), Team(name = "Team 4")),
                            Match(Team(name = "Team 5"), Team(name = "Team 6")),
                            Match(Team(name = "Team 1"), Team(name = "Team 3")),
                            Match(Team(name = "Team 2"), Team(name = "Team 3")),

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

                        }){
                        Text(text = "Anwesenheitscheck")
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = if (bothTeamsChecked) Color.Green else Color.Red),
                        onClick = {

                        }){
                        Text(text = "Start Match")
                    }

                    //Text(text = "Test")
                    //Text(text = "ABC")
                }

            }
        }


    }

    public fun spinnerMatch() {

    }

}