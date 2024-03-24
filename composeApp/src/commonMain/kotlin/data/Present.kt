package data

import enums.Attendance

data class Present(
    val match: Match = Match(),
    val player: Player = Player(),
    val present: Attendance? = null
){

}
