import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.LoginScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val appViewModel = AppViewModel()

        Navigator(screen = LoginScreen(appViewModel)){
            SlideTransition(it)
        }

        //var showContent by remember { mutableStateOf(false) }
        //val greeting = remember { Greeting().greet() }
        //Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        //    Button(onClick = { showContent = !showContent }) {
        //        Text("Click me!")
        //    }
        //    AnimatedVisibility(showContent) {
        //        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        //            Image(painterResource("compose-multiplatform.xml"), null)
        //            Text("Compose: $greeting")
        //        }
        //    }
        //}
    }
}