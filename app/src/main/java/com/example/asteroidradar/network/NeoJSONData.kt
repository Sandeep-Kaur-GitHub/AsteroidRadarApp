package com.example.asteroidradar.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "Neo_json_data_table")
data class NeoJSONData(
    @PrimaryKey
    @ColumnInfo(name="Neo_id")
    val id: Long,

    @ColumnInfo(name="Key")
    val keyOfDate:String,

    @ColumnInfo(name="Neo_codename")
    val codename: String,

    @ColumnInfo(name="Neo_absoluteMagnitude")
    val absoluteMagnitude: Double,

    @ColumnInfo(name="is_potentially_hazardous_asteroid")
    val is_potentially_hazardous_asteroid:Boolean,

    @ColumnInfo(name="max_diameter")
    val max_diameter:Double,

    @ColumnInfo(name="relative_velocity")
    val relative_velocity:String,

    @ColumnInfo(name = "distance_from_earth")
    val distance_from_earth:String
    )

data class PictureOfTheDay
    (@Json(name = "media_type") val mediaType: String,
     val title: String,
     val url: String)