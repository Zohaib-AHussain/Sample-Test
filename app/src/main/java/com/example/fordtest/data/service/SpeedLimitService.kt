package com.example.fordtest.data.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn

class SpeedLimitService {

    val speedLimit: Flow<Int> = flow {
        while (true) {
            val speedLimit = (MIN_SPEED_LIMIT..MAX_SPEED_LIMIT).random()
            emit(speedLimit)
            delay(SPEED_LIMIT_DELAY)
        }
    }.flowOn(Dispatchers.Default)

    companion object {
        const val MIN_SPEED_LIMIT = 80
        const val MAX_SPEED_LIMIT = 150
        const val SPEED_LIMIT_DELAY = 5000L
    }
}