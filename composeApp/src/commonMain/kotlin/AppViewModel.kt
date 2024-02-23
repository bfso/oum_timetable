import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import data.AppData

class AppViewModel {
    var data by  mutableStateOf(AppData())

}