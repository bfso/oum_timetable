package data

import java.time.LocalDate

class DataLocal :DataProvider,DataWriter{
    //Gets Data From the Database once implemented
    override fun getData(date: LocalDate): List<Match> {
        TODO("Not yet implemented")
    }

    //Write Data to the local Database
    override fun writeData(): Boolean {
        TODO("Not yet implemented")
    }

}