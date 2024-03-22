package data

data class Team(
    var name:String = "TEAM NAME",
    var checked:Boolean = false,
    var members:List<Player> = listOf(
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player(),
        Player()
    )
)
