package data

import java.time.LocalDate

class DataTest : DataProvider {
    val teams: List<Team> = listOf(
        Team(name = "Team 1"),
        Team(name = "Team 2"),
        Team(name = "Team 3"),
        Team(name = "Team 4"),
        Team(name = "Team 5"),
        Team(name = "Team 6")
    )

    val matches: List<Match> = listOf(
        Match(team1 = findTeamByName("Team 1"), team2 = findTeamByName("Team 2")),
        Match(team1 = findTeamByName("Team 3"), team2 = findTeamByName("Team 4")),
        Match(team1 = findTeamByName("Team 5"), team2 = findTeamByName("Team 6")),
        Match(team1 = findTeamByName("Team 1"), team2 = findTeamByName("Team 3")),
        Match(team2 = findTeamByName("Team 6"), team1 = findTeamByName("Team 2"))
    )

    private fun findTeamByName(name: String): Team {
        return teams.first { team -> team.name == name }
    }

    override fun getData(date: LocalDate): List<Match> {
        return matches
    }
}
