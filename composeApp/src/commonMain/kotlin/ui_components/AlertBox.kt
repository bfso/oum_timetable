package ui_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AlertBox(
    showAlertBox: Boolean,
    errorMessage: String,
    onDismiss: () -> Unit
) {
    if (showAlertBox) {
        AlertDialog(
            onDismissRequest = onDismiss,
            buttons = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Button(
                        onClick = onDismiss
                    ) {
                        Text(text = "Close")
                    }
                }
            },
            title = {
                Text(
                    text = "Error",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            contentColor = Color.Red,
            modifier = Modifier.size(width = 300.dp, height = 200.dp),
            backgroundColor = Color.Gray
        )
    }
}