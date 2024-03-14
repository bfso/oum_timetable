import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.onClick
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import data.Match
import data.Player
import java.awt.Cursor

//@Composable
//fun DropdownMenu(options: List<String>, onChange: (String)->Unit) {
//    var expanded by mutableStateOf(false)
//    Box {
//        TextField(
//            value = " ",
//            onValueChange = {}
//        )
//        androidx.compose.material.DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = {expanded = false},
//            ){
//            options.forEach{
//                DropdownMenuItem(
//                    onClick = {}
//                ){
//                    Text(text = it)
//                }
//            }
//
//
//        }
//    }
//}

@OptIn(ExperimentalFoundationApi::class, ExperimentalStdlibApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun DropdownMenuMatches(
    modifier: Modifier = Modifier,
    matches:List<Match>,
    value:String,
    label:String,
    onItemClick: (Match)->Unit
){
    var expanded by remember { mutableStateOf(false) }

    var icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var labelColor = if (expanded){
        MaterialTheme.colors.primary.copy(ContentAlpha.high)
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineColor = if (expanded){
        MaterialTheme.colors.primary
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineThickness = if(expanded){
        2.dp
    }else{
        1.dp
    }
    Box(modifier=modifier){
        TextField(
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = MaterialTheme.colors.onSurface,
                disabledLabelColor = labelColor

            ),
            modifier = Modifier
                .focusable(enabled = true)
                .onClick {expanded = !expanded}
                .indicatorLine(
                    enabled = true,
                    isError = false,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledIndicatorColor = bottomLineColor,
                        unfocusedIndicatorColor = bottomLineColor,
                    ),
                    unfocusedIndicatorLineThickness = bottomLineThickness
                )
                .pointerHoverIcon(
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)),
                    overrideDescendants = true
                ),
            readOnly = true,
            value =  value,
            singleLine = true,
            onValueChange = {},
            trailingIcon = { Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )},
            label = { Text(text = label)},
        )

        DropdownMenu(
            modifier = Modifier
                .pointerHoverIcon(
                    overrideDescendants = true,
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                ),
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ){
            matches.forEach{
                DropdownMenuItem(
                    onClick = {
                        onItemClick(it)
                        expanded = false
                    }
                ){
                    Text(text = "${it.team1.name} vs. ${it.team2.name}")
                }
            }
        }

    }
}



@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuStrings(
    modifier: Modifier = Modifier,
    items:List<String>,
    value:String,
    label:String,
    onItemClick: (String)->Unit
){
    var expanded by remember { mutableStateOf(false) }

    var icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var labelColor = if (expanded){
        MaterialTheme.colors.primary.copy(ContentAlpha.high)
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineColor = if (expanded){
        MaterialTheme.colors.primary
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineThickness = if(expanded){
        2.dp
    }else{
        1.dp
    }
    Box(modifier=modifier){
        TextField(
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = MaterialTheme.colors.onSurface,
                disabledLabelColor = labelColor

            ),
            modifier = Modifier
                .focusable(enabled = true)
                .onClick {expanded = !expanded}
                .indicatorLine(
                    enabled = true,
                    isError = false,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledIndicatorColor = bottomLineColor,
                        unfocusedIndicatorColor = bottomLineColor,
                    ),
                    unfocusedIndicatorLineThickness = bottomLineThickness
                )
                .pointerHoverIcon(
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)),
                    overrideDescendants = true
                ),
            readOnly = true,
            value =  value,
            singleLine = true,
            onValueChange = {},
            trailingIcon = { Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )},
            label = { Text(text = label)},
        )

        DropdownMenu(
            modifier = Modifier
                .pointerHoverIcon(
                    overrideDescendants = true,
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                ),
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ){
            items.forEach{
                DropdownMenuItem(
                    onClick = {
                        onItemClick(it)
                        expanded = false
                    }
                ){
                    Text(text = it)
                }
            }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuPlayers(
    modifier: Modifier = Modifier,
    players:List<Player>,
    value:String,
    label:String,
    onItemClick: (Player)->Unit
){
    var expanded by remember { mutableStateOf(false) }

    var icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var labelColor = if (expanded){
        MaterialTheme.colors.primary.copy(ContentAlpha.high)
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineColor = if (expanded){
        MaterialTheme.colors.primary
    }else{
        MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
    }
    var bottomLineThickness = if(expanded){
        2.dp
    }else{
        1.dp
    }
    Box(modifier=modifier){
        TextField(
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = MaterialTheme.colors.onSurface,
                disabledLabelColor = labelColor

            ),
            modifier = Modifier
                .focusable(enabled = true)
                .onClick {expanded = !expanded}
                .indicatorLine(
                    enabled = true,
                    isError = false,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledIndicatorColor = bottomLineColor,
                        unfocusedIndicatorColor = bottomLineColor,
                    ),
                    unfocusedIndicatorLineThickness = bottomLineThickness
                )
                .pointerHoverIcon(
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)),
                    overrideDescendants = true
                ),
            readOnly = true,
            value =  value,
            singleLine = true,
            onValueChange = {},
            trailingIcon = { Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight()
            )},
            label = { Text(text = label)},
        )

        DropdownMenu(
            modifier = Modifier
                .pointerHoverIcon(
                    overrideDescendants = true,
                    icon = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
                ),
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ){
            players.forEach{
                DropdownMenuItem(
                    onClick = {
                        onItemClick(it)
                        expanded = false
                    }
                ){
                    Text(text = "${it.firstName} ${it.name} ${it.playerNumber}")
                }
            }
        }

    }
}