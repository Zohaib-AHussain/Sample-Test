package com.example.fordtest.ui.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fordtest.data.service.SpeedService
import com.example.fordtest.data.service.SpeedLimitService

class SpeedMeterVMFactory(
    private val application: Application,
    private val speedService: SpeedService,
    private val speedLimitService: SpeedLimitService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SpeedMeterViewModel::class.java)) {
            SpeedMeterViewModel(this.application, this.speedService, this.speedLimitService) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}