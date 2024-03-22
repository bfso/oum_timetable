package data

import enums.Attendance

data class Check(
    val match: Match = Match(),
    val team: Team = Team(),
    var checked:Boolean = false,
)