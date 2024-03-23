package timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class TimerTestScreen:Screen {
    @Composable
    override fun Content() {
        var stopWatch = remember {StopWatch()}

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            StopWatchDisplay(
                formattedTime = stopWatch.formattedTime,
                onStartClick = stopWatch::start,
                onPauseClick = stopWatch::pause ,
                onResetClick = stopWatch::reset
            )
        }
    }
}