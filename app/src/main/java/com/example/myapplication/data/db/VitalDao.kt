package com.example.myapplication.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalDao {
    @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<VitalEntity>>

    @Insert
    suspend fun insertVital(vital: VitalEntity)
}