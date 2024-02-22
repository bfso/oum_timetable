package data

data class Match(
    val finished: Boolean = false,
    val team1:Team = Team(),
    val team2:Team = Team()
)
