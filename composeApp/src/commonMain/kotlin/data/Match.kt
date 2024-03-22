package data

data class Match(
    var finished: Boolean = false,
    val team1:Team = Team(),
    val team2:Team = Team()
)
