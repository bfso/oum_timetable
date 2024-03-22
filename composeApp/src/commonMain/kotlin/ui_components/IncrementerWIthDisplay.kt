package ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import screens.game_manager.GameManager

@Composable
fun IncrementerWithDisplay(
    modifier: Modifier = Modifier,
    minIndex: Int? = null,
    maxIndex: Int? = null,
    startingIndex: Int = 0,
    //TODO These Composables don't get centered inside the Button
    decrementSymbol:@Composable RowScope.() -> Unit = {Text("-")},
    incrementSymbol:@Composable RowScope.() -> Unit = {Text("+")},
    display:(Int)->String = {"$it"}
    ) {
    minIndex?.let { if (it > startingIndex) throw IndexOutOfBoundsException() }
    maxIndex?.let { if (it < startingIndex) throw IndexOutOfBoundsException() }

    var index by remember{ mutableStateOf(startingIndex)}
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(
            enabled = minIndex?.let { index > it } ?: true,
            modifier = Modifier.weight(1f).fillMaxSize(),
            onClick = { index-- },
            shape = RoundedCornerShape(CornerSize(15.dp)),
            content = decrementSymbol
        )
        Box(
            modifier = Modifier.background(
                color = GameManager.containerColor,
                shape = RoundedCornerShape(corner = CornerSize(15.dp))
            )
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = display(index), fontSize = 30.sp)
        }
        Button(
            enabled = maxIndex?.let { index < it } ?: true,
            modifier = Modifier.weight(1f).fillMaxSize(),
            onClick = { index++ },
            shape = RoundedCornerShape(CornerSize(15.dp)),
            content = incrementSymbol
        )
    }
}