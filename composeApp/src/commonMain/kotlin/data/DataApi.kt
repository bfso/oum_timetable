package data

import java.time.LocalDate

class DataApi:DataProvider,DataWriter{
    override fun getData(date: LocalDate): List<Match> {
        // Get data from API once API is working
        TODO("Not yet implemented")
    }

    override fun writeData(): Boolean {
        // Post data to API once API is working
        TODO("Not yet implemented")
    }
}