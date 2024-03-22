package data

import androidx.compose.ui.graphics.vector.ImageVector
import enums.Attendance

data class Player(
    val name: String = "Nachname",
    val firstName:String = "Vorname",
    val playerNumber: Int = -1,
    val licenceNumber: Int = -1,
    val image: ImageVector? = null,
)
