import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import oum_timetable.composeapp.generated.resources.Res
import oum_timetable.composeapp.generated.resources.unihockey_logo
import java.awt.Dimension

@OptIn(ExperimentalResourceApi::class)
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "oum_timetable",
        icon = painterResource(Res.drawable.unihockey_logo)
    ) {
        window.minimumSize = Dimension(800,500)
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}
