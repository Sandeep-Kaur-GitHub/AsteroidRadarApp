package com.example.asteroidradar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.network.NeoAPI
import com.example.asteroidradar.network.NeoJSONData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getNeoWSProperties()
    }

    private fun getNeoWSProperties() {
        GlobalScope.launch {
            val stringResponse = NeoAPI.retrofitService.getProperties()
            val parsedResponse = parseAsteroidsJsonResult(JSONObject(stringResponse))
            _response.postValue(parsedResponse.size.toString())
        }
    }
}
fun parseAsteroidsJsonResult(jsonObject: JSONObject): List<NeoJSONData> {
    val asteroidList = mutableListOf<NeoJSONData>()
    val nearEarthObjectsJson = jsonObject.getJSONObject("near_earth_objects")
    val dateList = nearEarthObjectsJson.keys()
    val dateListSorted = dateList.asSequence().sorted()
    dateListSorted.forEach {
        val key: String = it
        val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(key)
        asteroidList.add(NeoJSONData(element_count = dateAsteroidJsonArray.length()))
    }
    return asteroidList
}
