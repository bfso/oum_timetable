package data

import java.time.LocalDate

interface DataProvider {
    fun getData(date:LocalDate = LocalDate.now()):List<Match>;
}


