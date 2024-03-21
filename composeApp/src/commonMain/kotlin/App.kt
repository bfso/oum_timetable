import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import data.DataTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.login.LoginScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val appViewModel = AppViewModel(DataTest())
        Scaffold (
            modifier = Modifier
        ){
            Navigator(screen = LoginScreen(appViewModel = appViewModel))
            //{
            //    SlideTransition(it)
            //}
        }
    }
}