package com.example.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://api.nasa.gov/neo/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit= Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface NeoWebServiceAPI{
    @GET("rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    suspend fun getProperties(): String
}
object NeoAPI{
    val retrofitService : NeoWebServiceAPI by lazy {
        retrofit.create(NeoWebServiceAPI::class.java)
    }
}
