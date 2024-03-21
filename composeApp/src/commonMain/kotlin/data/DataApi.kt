package data

import java.time.LocalDate

class DataApi:DataProvider,DataWriter{
    override fun getData(date: LocalDate): List<Match> {
        TODO("Not yet implemented")
    }

    override fun writeData(): Boolean {
        TODO("Not yet implemented")
    }
}