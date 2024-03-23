package timer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWatchDisplay(
    formattedTime:String,
    onStartClick: ()->Unit,
    onPauseClick: ()-> Unit,
    onResetClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text= formattedTime,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color.Black
        )
        Spacer(Modifier.height(16.dp))
        Row (
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ){
            Button(content = { Text("Start")}, onClick = onStartClick)
            Button(content = { Text("Pause")}, onClick = onPauseClick)
            Button(content = { Text("Reset")}, onClick = onResetClick)
        }
    }
}