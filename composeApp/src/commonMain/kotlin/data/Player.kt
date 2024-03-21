package data

import androidx.compose.ui.graphics.vector.ImageVector
import enums.Attendance

data class Player(
    val name: String = "UNDEFINED NAME",
    val firstName:String = "UNDEFINED FIRST NAME",
    val playerNumber: Int = -1,
    val licenceNumber: Int = -1,
    val image: ImageVector? = null,
    val present: Attendance = Attendance.NONE
)
