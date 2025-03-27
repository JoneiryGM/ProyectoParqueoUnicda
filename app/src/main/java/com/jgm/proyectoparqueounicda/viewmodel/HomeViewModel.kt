package com.jgm.proyectoparqueounicda.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgm.proyectoparqueounicda.model.businees.Parking
import com.jgm.proyectoparqueounicda.model.businees.ParkingConfig
import com.jgm.proyectoparqueounicda.model.repository.FirestoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FirestoreRepository) : ViewModel() {

    private val _currentQtyParking = MutableStateFlow<ParkingConfig?>(null)
    val currentQtyParking: StateFlow<ParkingConfig?> = _currentQtyParking

    private val _parking = MutableStateFlow<List<Parking>>(emptyList())
    val parking: StateFlow<List<Parking>> = _parking

    fun fetchCurrentParkingConfig() {
        Log.d("HomeViewModel", "calling fetchCurrentParkingConfig")
        viewModelScope.launch {
            repository.getIndexParking().collect { parkingConf ->
                _currentQtyParking.value = parkingConf
            }
        }
    }

    fun fetchAllParking() {
        Log.d("HomeViewModel", "calling fetchAllParking")
        viewModelScope.launch {
            repository.getParking().collect { parking ->
                _parking.value = parking
            }
        }
    }

    fun updateConfigParking(parkingConfig: ParkingConfig) {
        Log.d("HomeViewModel", "calling updateConfigParking")
        viewModelScope.launch {
            repository.updateSettingsParking(parkingConfig)
        }
    }

    fun updateParking(parking: Parking) {
        Log.d("HomeViewModel", "calling updateParking")
        viewModelScope.launch {
            repository.updateParkingAvailability(parking)
        }
    }

    fun adjustParkingSlot(qty: Int) {
        Log.d("HomeViewModel", "calling adjustParkingSlot")
        viewModelScope.launch {
            repository.adjustParkingIndex(qty)
        }
    }

}