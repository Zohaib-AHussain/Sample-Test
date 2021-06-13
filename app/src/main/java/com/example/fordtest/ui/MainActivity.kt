package com.example.fordtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fordtest.*
import com.example.fordtest.data.service.SpeedLimitService
import com.example.fordtest.data.service.SpeedService
import com.example.fordtest.databinding.ActivityMainBinding
import com.example.fordtest.ui.vm.SpeedMeterViewModel
import com.example.fordtest.ui.vm.SpeedMeterVMFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SpeedMeterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, SpeedMeterVMFactory(this.application, SpeedService(), SpeedLimitService()))
                .get(SpeedMeterViewModel::class.java)

        viewModel.speedMeterInfo.observe(this, {
            with(binding) {
                speedometer.apply {
                    speedTo(it.currentSpeed.toFloat())
                    speedLimit = it.speedLimit.toFloat()
                }
                speedWarning.visibility = if (it.overSpeed) View.VISIBLE else View.GONE
            }
        })
    }
}