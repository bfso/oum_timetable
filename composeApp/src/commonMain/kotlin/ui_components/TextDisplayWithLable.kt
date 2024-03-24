package ui_components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextDisplayWithLabel(
    modifier: Modifier = Modifier,
    value:String,
    label:String,

){
    TextField(
        readOnly = true,
        singleLine =  true,
        modifier = modifier,
        value=value,
        label={Text(text = label)},
        onValueChange = {},
        //colors = TextFieldDefaults.textFieldColors(
        //    unfocusedLabelColor = MaterialTheme.colors.primary.copy(ContentAlpha.high),
        //    focusedLabelColor = MaterialTheme.colors.primary.copy(ContentAlpha.high)
        //),
    )
}