package screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import data.Match

class ChooseTeamScreen (private val match:Match):Screen {
    @Composable
    override fun Content() {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column (
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                ){
                    Text(text = match.team1.name)
                }
                Spacer(Modifier.height(40.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(60.dp)
                ){
                    Text(text = match.team2.name)
                }
            }
        }
    }
}