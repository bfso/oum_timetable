package data

data class Team(
    val name:String,
    val members:Array<Player>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (name != other.name) return false
        if (!members.contentEquals(other.members)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + members.contentHashCode()
        return result
    }
}
