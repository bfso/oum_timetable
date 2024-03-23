package timer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class StopWatch {
    var formattedTime by mutableStateOf("00:00:000")

    private var coroutineScope = CoroutineScope(Dispatchers.Main)
    private var isRunning = false

    private var timeMillis = 0L
    private var lastTimestamp = 0L

    fun start(){
        if(isRunning)return
        coroutineScope.launch {
            lastTimestamp = System.currentTimeMillis()
            isRunning = true
            while (isRunning){
                delay(10L)
                timeMillis+= System.currentTimeMillis()-lastTimestamp
                lastTimestamp = System.currentTimeMillis()
                formattedTime = formatTime(timeMillis)
            }
        }
    }

    private fun formatTime(timeMillis: Long):String {
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timeMillis),
            ZoneId.systemDefault()
        )
        val formatter = DateTimeFormatter.ofPattern(
            "mm:ss:SSS",
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
        timeMillis = 0L
        lastTimestamp = 0L
        formattedTime = formatTime(timeMillis)
    }


}