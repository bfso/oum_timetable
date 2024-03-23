package timer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class Timer(
    val format:String = "mm:ss:SS",
    val durationMillis:Long,
    var onTimerFinish:()->Unit = {}
) {


    var formattedTime by mutableStateOf(formatTime(durationMillis))

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    var isRunning by mutableStateOf(false)
        private set

    private var timeMillis = durationMillis
    private var lastTimestamp = 0L

    fun start(){
        if(isRunning)return
        isRunning = true
        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()

            while (isRunning && timeMillis>0){
                delay(10L)
                timeMillis-= System.currentTimeMillis()-lastTimestamp
                lastTimestamp = System.currentTimeMillis()
                formattedTime = formatTime(timeMillis)
            }
            if (timeMillis<=0){
                onTimerFinish()
                reset()
            }
        }

    }

    private fun formatTime(timeMillis: Long):String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMillis),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern(
            //TODO What time pattern do we want?
            format,
            Locale.getDefault()
        )
        return localDateTime.format(formatter)
    }

    fun pause(){
        isRunning = false
    }

    fun reset(){
        coroutineScope.cancel()
        isRunning=false
        coroutineScope = CoroutineScope(Dispatchers.Main)
        timeMillis = durationMillis
        lastTimestamp = 0L
        formattedTime = formatTime(timeMillis)
    }


    fun toggleOnOff():Boolean{
        if (isRunning){
            pause()
        }else{
            start()
        }
        return isRunning
    }

}