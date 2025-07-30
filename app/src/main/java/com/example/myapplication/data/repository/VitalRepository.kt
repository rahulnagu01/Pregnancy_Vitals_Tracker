package com.example.myapplication.data.repository

import com.example.myapplication.data.db.VitalDao
import com.example.myapplication.data.db.VitalEntity
import kotlinx.coroutines.flow.Flow

class VitalRepository(private val dao: VitalDao) {
    val vitals: Flow<List<VitalEntity>> = dao.getAllVitals()

    suspend fun addVital(vital: VitalEntity) {
        dao.insertVital(vital)
    }
}
