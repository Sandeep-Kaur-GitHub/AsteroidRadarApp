package com.example.asteroidradar.network

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NeoJsonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(NeoAsteroid:NeoJSONData)

    @Query("SELECT * FROM Neo_json_data_table")
    fun getAllAsteroids(): LiveData<List<NeoJSONData>>

}