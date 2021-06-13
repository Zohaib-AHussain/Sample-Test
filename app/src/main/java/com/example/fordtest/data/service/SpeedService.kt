package com.example.fordtest.data.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn

class SpeedService {

    val currentSpeed: Flow<Int> = flow {
        while (true) {
            val speed = (MIN_SPEED..MAX_SPEED).random()
            emit(speed)
            delay(SPEED_DELAY)
        }
    }.flowOn(Dispatchers.Default)

    companion object {
        const val MIN_SPEED = 0
        const val MAX_SPEED = 150
        const val SPEED_DELAY = 2000L
    }
}