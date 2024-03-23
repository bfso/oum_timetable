import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import data.DataTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.game_manager.GameManager
import screens.login.LoginScreen
import timer.TimerTestScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val appViewModel = AppViewModel(DataTest())

        //TODO remove these once finished
        //appViewModel.currentMatch = appViewModel.matches[0]
        Scaffold (
            modifier = Modifier
        ){
            Navigator(screen = LoginScreen(appViewModel = appViewModel))
            //Navigator(screen = GameManager(appViewModel = appViewModel))
            //Navigator(screen = TimerTestScreen())


            //{
            //    SlideTransition(it)
            //}
        }
    }
}