package com.example.asteroidradar.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Neo_json_data_table")
data class NeoJSONData(
    @PrimaryKey
    @ColumnInfo(name="Neo_id")
    val id: Long,

    @ColumnInfo(name="Neo_codename")
    val codename: String,

    @ColumnInfo(name="Neo_absoluteMagnitude")
    val absoluteMagnitude: Double
    )
