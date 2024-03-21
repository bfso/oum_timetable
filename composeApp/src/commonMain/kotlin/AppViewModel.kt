import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.AppData
import data.Match

class AppViewModel {
    var data by  mutableStateOf(AppData())
    var currentMatch: Match? by mutableStateOf( null )
    var team1Ready by mutableStateOf( false )
    var team2Ready by mutableStateOf( false )
}