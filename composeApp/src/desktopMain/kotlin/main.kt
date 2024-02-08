import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import screens.HomeScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "oum_timetable") {
        //App()
        HomeScreen().Content()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}

@Preview
@Composable
fun DesktopLoginPreview() {
    LoginScreen()
}