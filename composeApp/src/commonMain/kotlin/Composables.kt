import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun DropdownMenu(options: List<String>, onChange: (String)->Unit) {
    var expanded by mutableStateOf(false)
    Box {
        TextField(
            value = " ",
            onValueChange = {}
        )
        androidx.compose.material.DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            ){
            options.forEach{
                DropdownMenuItem(
                    onClick = {}
                ){
                    Text(text = it)
                }
            }


        }
    }
}