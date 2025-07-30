package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals")
data class VitalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val weight: Float,
    val babyKicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)