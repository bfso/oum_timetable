package data

import java.time.LocalDate

class DataTest : DataProvider {
    val matches: List<Match> = listOf(
        Match(team1 = Team(name = "Team 1"), team2 =  Team(name = "Team 2")),
        Match(team1 = Team(name = "Team 3"), team2 = Team(name = "Team 4")),
        Match(team1 = Team(name = "Team 5"), team2 = Team(name = "Team 6")),
        Match(team1 = Team(name = "Team 1"), team2 = Team(name = "Team 3")),
        Match(team1 = Team(name = "Team 2"), team2 = Team(name = "Team 4")),
    )

    override fun getData(date: LocalDate): List<Match> {
        return matches
    }



}
