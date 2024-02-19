package data

data class Team(
    val name:String = "UNDEFINED TEAM NAME",
    val checked:Boolean = false,
    val members:List<Player> = listOf(
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
