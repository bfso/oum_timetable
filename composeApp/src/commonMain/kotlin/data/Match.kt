package data

data class Match(
    var finished: Boolean = false,
    val team1:Team = Team(),
    val team2:Team = Team(),
    var team1Ready:Boolean = false,
    var team2Ready:Boolean = false,
    var team1Goals:Int = 0,
    var team2Goals:Int = 0
)
