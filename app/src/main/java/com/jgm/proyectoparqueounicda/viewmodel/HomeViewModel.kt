package com.jgm.proyectoparqueounicda.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgm.proyectoparqueounicda.model.businees.ParkingConfig
import com.jgm.proyectoparqueounicda.model.repository.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: FirestoreRepository) : ViewModel() {

    private val _currentQtyParking = MutableStateFlow<ParkingConfig?>(null)
    val currentQtyParking: StateFlow<ParkingConfig?> = _currentQtyParking

    fun fetchCurrentParkingConfig() {
        Log.d("HomeViewModel", "calling fetchCurrentParkingConfig")
        viewModelScope.launch {
            repository.getIndexParkings().collect { parkingConf ->
                _currentQtyParking.value = parkingConf
            }
        }
    }

    suspend fun updateConfigParking(parkingConfig: ParkingConfig) {
        Log.d("HomeViewModel", "calling updateConfigParking")
        viewModelScope.also {
            repository.updateSettingsParking(parkingConfig)
        }
    }

}