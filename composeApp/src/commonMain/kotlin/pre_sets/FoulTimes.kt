package pre_sets

val foulTimes:List<Int> = listOf(2,4,10,20)

enum class FoulTimes(val timeInMinutes: Int) {
    TWO(2),
    FOUR(4),
    TEN(10),
    REST_OF_MATCH(20)
}