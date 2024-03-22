import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.Match
import data.Team
import screens.ChooseTeamScreen
import screens.TeamOverviewScreen
import java.awt.Dimension

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "oum_timetable") {
        window.minimumSize = Dimension(800,500)
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}
