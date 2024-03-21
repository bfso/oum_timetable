import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.DataProvider
import data.DataTest
import data.Match

class AppViewModel(
    data:DataProvider = DataTest()
) {
    var matches:List<Match> by  mutableStateOf(listOf())
    var currentMatch: Match? by mutableStateOf( null )
    var team1Ready by mutableStateOf( false )
    var team2Ready by mutableStateOf( false )

    init {
        matches = data.getData()
    }
}