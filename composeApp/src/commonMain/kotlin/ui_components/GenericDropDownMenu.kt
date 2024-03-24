package ui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class, ExperimentalStdlibApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun <T> GenericDropdownMenu(
    modifier: Modifier = Modifier,
    iterable: Iterable<T>,
    startingValue:String,
    label:String,
    toString:(T)->String,
    onItemClick: (T)->Unit,
    textColor:Color? = null,
    textStyle:TextStyle = TextStyle()
){
    var expanded by remember { mutableStateOf(false) }
    var dropDownValue: T? by remember { mutableStateOf(null)
    }


    var bottomLineThickness: Dp
    var icon : ImageVector
    var labelColor: Color
    var bottomLineColor:Color

    if(expanded){
        icon = Icons.Filled.KeyboardArrowUp
        labelColor = MaterialTheme.colors.primary.copy(ContentAlpha.high)
        bottomLineColor = MaterialTheme.colors.primary
        bottomLineThickness = 2.dp
    }else{
        icon = Icons.Filled.KeyboardArrowDown
        bottomLineColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
        labelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
        bottomLineThickness = 1.dp
    }

    Box(modifier=modifier){
        TextField(
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = textColor?:MaterialTheme.colors.onSurface,
                disabledLabelColor = labelColor,
            ),
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxSize()
                .focusable(enabled = true)
                .clickable {expanded = !expanded}
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
                    icon = PointerIcon.Hand,
                    overrideDescendants = true
                ),
            readOnly = true,
            value =  dropDownValue?.let{toString(dropDownValue!!)}?:startingValue,
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
                    icon = PointerIcon.Hand,
                    overrideDescendants = true
                ),
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ){
            iterable.forEach{
                DropdownMenuItem(
                    onClick = {
                        dropDownValue = it
                        onItemClick(it)
                        expanded = false
                    }
                ){
                    Text(text = toString(it))
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalStdlibApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun <T> GenericDropdownMenu(
    modifier: Modifier = Modifier,
    iterable: Iterable<T>,
    label:String,
    toString:(T)->String,
    onItemClick: (T)->Unit,
    textColor:Color? = null,
    textStyle:TextStyle = TextStyle()
){
    var expanded by remember { mutableStateOf(false) }
    var dropDownValue: T? by remember { mutableStateOf(iterable.firstOrNull())
    }


    var bottomLineThickness: Dp
    var icon : ImageVector
    var labelColor: Color
    var bottomLineColor:Color

    if(expanded){
        icon = Icons.Filled.KeyboardArrowUp
        labelColor = MaterialTheme.colors.primary.copy(ContentAlpha.high)
        bottomLineColor = MaterialTheme.colors.primary
        bottomLineThickness = 2.dp
    }else{
        icon = Icons.Filled.KeyboardArrowDown
        bottomLineColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
        labelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
        bottomLineThickness = 1.dp
    }

    Box(modifier=modifier){
        TextField(
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = textColor?:MaterialTheme.colors.onSurface,
                disabledLabelColor = labelColor,
            ),
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxSize()
                .focusable(enabled = true)
                .clickable {expanded = !expanded}
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
                    icon = PointerIcon.Hand,
                    overrideDescendants = true
                ),
            readOnly = true,
            value =  dropDownValue?.let{toString(dropDownValue!!)}?:"ERROR: No elements to choose from",
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
                    icon = PointerIcon.Hand,
                    overrideDescendants = true
                ),
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ){
            iterable.forEach{
                DropdownMenuItem(
                    onClick = {
                        dropDownValue = it
                        onItemClick(it)
                        expanded = false
                    }
                ){
                    Text(text = toString(it))
                }
            }
        }

    }
}