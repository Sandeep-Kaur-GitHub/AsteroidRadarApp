package com.example.asteroidradar.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://api.nasa.gov/neo/"
private val retrofit= Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface NeoWebServiceAPI{
    @GET("rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY")
    fun getProperties(): Call<String>
}
object NeoAPI{
    val retrofitService : NeoWebServiceAPI by lazy {
        retrofit.create(NeoWebServiceAPI::class.java)
    }
}