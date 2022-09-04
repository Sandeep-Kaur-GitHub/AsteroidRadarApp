package com.example.asteroidradar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.network.NeoAPI
import com.example.asteroidradar.network.NeoDatabase
import com.example.asteroidradar.network.NeoJSONData
import com.example.asteroidradar.network.PictureOfTheDay
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.get
import org.json.JSONObject
import kotlin.collections.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _resImg = MutableLiveData<PictureOfTheDay>()
    val resImg: LiveData<PictureOfTheDay>
        get() = _resImg

    private val database by lazy {
        NeoDatabase.getInstance(application)
    }
    val allData = database.neoJsonDao.getAllAsteroids()

    init {
        getNeoWSProperties()
        getImageOfTheDay()
    }

    private fun getNeoWSProperties() {
        GlobalScope.launch {
            val stringResponse = NeoAPI.retrofitService.getProperties()
            val parsedResponse = parseAsteroidsJsonResult(JSONObject(stringResponse))
            parsedResponse.forEach {
                database.neoJsonDao.insert(it)
            }
            _response.postValue(parsedResponse.toString())
        }
    }
    private fun  getImageOfTheDay(){
        GlobalScope.launch {

            val ImageResponse= NeoAPI.retrofitService.getImageOfTheDay()
            val imgUrl =ImageResponse.url
            val imgDes=ImageResponse.title
            val imgMedia=ImageResponse.mediaType
            val imgDataclass= PictureOfTheDay(imgMedia,imgDes,imgUrl)
            _resImg.postValue(imgDataclass)
        }
    }

    fun parseAsteroidsJsonResult(jsonResult: JSONObject): ArrayList<NeoJSONData> {
        val nearEarthObjectsJson = jsonResult.getJSONObject("near_earth_objects")
        val asteroidList = ArrayList<NeoJSONData>()
        val key = nearEarthObjectsJson.keys().iterator().next()
        Log.i("Datekeyyy", "" + key)
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray("2022-09-02")
        for (i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
            val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val hazardous = asteroidJson.getBoolean("is_potentially_hazardous_asteroid")
            val asteroid = NeoJSONData(id,key,codename, absoluteMagnitude,hazardous)
            asteroidList.add(asteroid)
        }
        return asteroidList
    }
}

