package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.VitalEntity
import com.example.myapplication.data.repository.VitalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalViewModel(private val repository: VitalRepository) : ViewModel() {

    val vitals: StateFlow<List<VitalEntity>> = repository.vitals.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addVital(systolic: Int, diastolic: Int, heartRate: Int, weight: Float, babyKicks: Int) {
        viewModelScope.launch {
            repository.addVital(
                VitalEntity(
                    systolic = systolic,
                    diastolic = diastolic,
                    heartRate = heartRate,
                    weight = weight,
                    babyKicks = babyKicks
                )
            )
        }
    }
}
