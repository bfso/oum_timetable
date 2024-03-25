package data

import java.time.LocalDate

class DataTest : DataProvider {
    val players: List<Player> = listOf(
        Player(playerNumber = 1,name = "Mermod", firstName = "Damian", licenceNumber = 1),
        Player(playerNumber = 2,name = "Holzer", firstName = "Tobias",licenceNumber = 2),
        Player(playerNumber = 3,name = "Heberle", firstName = "Ivo",licenceNumber = 3),
        Player(playerNumber = 4,name = "Bozic", firstName = "Mario",licenceNumber = 4),
        Player(playerNumber = 5,name = "Kempf", firstName = "Dario",licenceNumber = 5),
        Player(playerNumber = 6,name = "Asani", firstName = "Luan",licenceNumber = 6),
        Player(playerNumber = 7,name = "Werlen", firstName = "Nevio",licenceNumber = 7),
        Player(playerNumber = 8,name = "Bodenman", firstName = "Jan",licenceNumber = 8),
        Player(playerNumber = 9,name = "Victoria", firstName = "Mohr",licenceNumber = 9),
        Player(playerNumber = 10,name = "Fux", firstName = "Mario",licenceNumber = 10),
        Player(playerNumber = 11,name = "Lehner", firstName = "Stephan",licenceNumber = 11),
        Player(playerNumber = 12,name = "Kippel", firstName = "Alain",licenceNumber = 12),
        Player(playerNumber = 13,name = "Ambord ", firstName = "Nadia",licenceNumber = 13),
        Player(playerNumber = 14,name = "Bayard ", firstName = "Pascal",licenceNumber = 14),
        Player(playerNumber = 15,name = "Dirren ", firstName = "Reinhard",licenceNumber = 15),
        Player(playerNumber = 16,name = "Heinzen ", firstName = "Marco",licenceNumber = 16),
        Player(playerNumber = 17,name = "Musterman ", firstName = "Max",licenceNumber = 17),
        Player(playerNumber = 18,name = "Karlen ", firstName = "Christoph",licenceNumber = 18),


    )

    val teams: List<Team> = listOf(
        Team(number = 1,name = "We are the champions", members = listOf(getPlayerByLicenceNumber(1),getPlayerByLicenceNumber(2),getPlayerByLicenceNumber(3))),
        Team(number = 2,name = "Loooooser Team", members = listOf(getPlayerByLicenceNumber(4),getPlayerByLicenceNumber(5),getPlayerByLicenceNumber(6))),
        Team(number = 3,name = "Team 3", members = listOf(getPlayerByLicenceNumber(7),getPlayerByLicenceNumber(8),getPlayerByLicenceNumber(9))),
        Team(number = 4,name = "Team 4", members = listOf(getPlayerByLicenceNumber(10),getPlayerByLicenceNumber(11),getPlayerByLicenceNumber(12))),
        Team(number = 5,name = "Team 5", members = listOf(getPlayerByLicenceNumber(13),getPlayerByLicenceNumber(14),getPlayerByLicenceNumber(15))),
        Team(number = 6,name = "Team 6", members = listOf(getPlayerByLicenceNumber(16),getPlayerByLicenceNumber(17),getPlayerByLicenceNumber(18)))
    )

    val matches = generateMatchMatrix(teams)
    //val matches: List<Match> = listOf(
    //    Match(team1 = getTeamByNumber(1), team2 = getTeamByNumber(2)),
    //    Match(team1 = getTeamByNumber(3), team2 = getTeamByNumber(4)),
    //    Match(team1 = getTeamByNumber(5), team2 = getTeamByNumber(6)),
    //    Match(team1 = getTeamByNumber(2), team2 = getTeamByNumber(3)),
    //    Match(team2 = getTeamByNumber(4), team1 = getTeamByNumber(5)),
    //    Match(team2 = getTeamByNumber(6), team1 = getTeamByNumber(1))
    //
    //)

    private fun findTeamByName(name: String): Team {
        return teams.first { team -> team.name == name }
    }

    private fun getTeamByNumber(number: Int): Team {
        return teams.first { team -> team.number == number }
    }

    override fun getData(date: LocalDate): List<Match> {
        return matches
    }

    private fun getPlayerByLicenceNumber(licenceNumber:Int):Player{
        return players.first{licenceNumber == it.licenceNumber}
    }

    private fun generateMatchMatrix(teams:List<Team>):List<Match>{
        val matches = mutableListOf<Match>()
        for (i in 0..<teams.lastIndex){
            for (j in i+1..teams.lastIndex){
                matches.add(Match(team1 = teams[i], team2 = teams[j]))
            }
        }

        return matches
    }
}
