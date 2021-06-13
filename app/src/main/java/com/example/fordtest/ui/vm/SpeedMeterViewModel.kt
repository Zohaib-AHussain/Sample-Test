package com.example.fordtest.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fordtest.data.service.SpeedLimitService
import com.example.fordtest.data.model.SpeedMeterInfo
import com.example.fordtest.data.service.SpeedService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class SpeedMeterViewModel(
    application: Application,
    speedService: SpeedService,
    speedLimitService: SpeedLimitService
) : AndroidViewModel(application) {

    private val _speedMeterInfo: MutableLiveData<SpeedMeterInfo> = MutableLiveData()
    val speedMeterInfo: LiveData<SpeedMeterInfo> = _speedMeterInfo

    init {
        viewModelScope.launch {
            speedService.currentSpeed.combine(speedLimitService.speedLimit) { speed, speedLimit ->
                SpeedMeterInfo(speed, speedLimit, speed > speedLimit)
            }.collect { value -> _speedMeterInfo.value = value }
        }
    }
}