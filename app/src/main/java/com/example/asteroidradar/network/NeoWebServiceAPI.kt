package com.example.asteroidradar.network

import android.widget.ImageView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl.get
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.lang.reflect.Array.get
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

private const val BASE_URL="https://api.nasa.gov/"
private const val API= "bgMTgQKJ4yIVgrQC9Y49CSI1EvLRktWce4USTeXC"

//var simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
//private const val currentDate = simpleDate.format(Date())



//https://api.nasa.gov/planetary/apod?api_key=bgMTgQKJ4yIVgrQC9Y49CSI1EvLRktWce4USTeXC
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit= Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface NeoWebServiceAPI{
    @GET("https://api.nasa.gov/neo/rest/v1/feed?start_date=2022-09-02&end_date=2022-09-02&api_key=${API}")
    suspend fun getProperties(): String

    @GET("planetary/apod?api_key=${API}")
    suspend fun getImageOfTheDay():PictureOfTheDay
}
object NeoAPI{
    val retrofitService : NeoWebServiceAPI by lazy {
        retrofit.create(NeoWebServiceAPI::class.java)
    }
}
