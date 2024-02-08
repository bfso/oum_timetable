package screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class HomeScreen :Screen{
    @Composable
    override fun Content() {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Box(modifier = Modifier){
                Column (){
                    Text(text = "Test")
                }
            }
        }


    }

}