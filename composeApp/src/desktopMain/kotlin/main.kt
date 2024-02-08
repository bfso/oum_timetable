import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.Match
import data.Team
import screens.ChooseTeamScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "oum_timetable") {
        //App()
        //LoginScreen()
        ChooseTeamScreen(Match(Team("Team1", arrayOf()),Team("Team2", arrayOf()))).Content()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}
