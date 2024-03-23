package timer


val Double.seconds:Long
    get() = (this*1000).toLong()

val Double.minutes:Long
    get() = (this*60_000).toLong()

val Double.hours:Long
    get() = (this*3_600_000).toLong()

val Double.days:Long
    get() = (this*24*3_600_000).toLong()


val Int.seconds:Long
    get() = this.toLong()*1000

val Int.minutes:Long
    get() = this.toLong()*60_000

val Int.hours:Long
    get() = this.toLong()*3_600_000

val Int.days:Long
    get() = this.toLong()*24*3_600_000


val Long.seconds:Long
    get() = this.toLong()*1000

val Long.minutes:Long
    get() = this.toLong()*60_000

val Long.hours:Long
    get() = this.toLong()*3_600_000

val Long.days:Long
    get() = this.toLong()*24*3_600_000