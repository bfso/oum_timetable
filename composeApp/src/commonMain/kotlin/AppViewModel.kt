import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import data.DataProvider
import data.DataTest
import data.Match

class AppViewModel(
    data:DataProvider = DataTest()
) :ScreenModel{
    var matches:List<Match> by  mutableStateOf(listOf())
    var currentMatch: Match? by mutableStateOf( null )

    init {
        matches = data.getData()
    }
}