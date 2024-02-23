package screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel


class LoginScreenModel :ScreenModel {
    //val test = listOf("Test","alshdfoas","aoisdof")

    //var username by   mutableStateOf("")
    //var password by   mutableStateOf("")
    var username by   mutableStateOf("")
    var password by   mutableStateOf("")
}
