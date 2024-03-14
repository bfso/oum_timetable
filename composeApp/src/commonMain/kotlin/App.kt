import androidx.compose.foundation.focusable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.Team
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.FoulScreen
import screens.LoginScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        val appViewModel = AppViewModel()
        //var alertBox by remember { mutableStateOf(false) }
        Scaffold (
            modifier = Modifier
                //.focusable()
                //.focusRequester(FocusRequester())
                //.onKeyEvent {
                //when (it.key){
                //    //Key.Escape->{ navigator.pop()}
                //    Key.Enter ->{
                //        alertBox = true
                //        true}
                //    else -> {false}
                //    }
                //}
        ){
            //if (alertBox){
            //    AlertDialog(
            //        onDismissRequest = {alertBox = false},
            //        confirmButton = {
            //            Button(
            //                onClick = {alertBox = false}
            //            ){
            //                Text(text = "OK")
            //            }

            //                        },
            //        text = { Text(text = "Enter was pressed") }
            //    )
            //}
            Navigator(screen = FoulScreen(Team()))
            //{
            //    SlideTransition(it)
            //}
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