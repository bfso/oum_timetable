import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.DataTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.context.startKoin
import org.koin.dsl.module
import screens.game_manager.GameManager
import screens.login.LoginScreen
import timer.TimerTestScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    initKoin()
    MaterialTheme {

        //val appViewModel = AppViewModel(DataTest())

        //TODO remove these once finished
        //appViewModel.currentMatch = appViewModel.matches[0]
        Scaffold (
            modifier = Modifier
        ){
            Navigator(screen = LoginScreen())
            //Navigator(screen = GameManager(appViewModel = appViewModel))
            //Navigator(screen = TimerTestScreen())
            {
                SlideTransition(it)
            }
        }
    }
}

val myModule = module {
    factory { AppViewModel(DataTest()) }
}

fun initKoin(){
    startKoin{
        modules(myModule)
    }
}



