package com.example.asteroidradar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.network.NeoAPI
import com.example.asteroidradar.network.NeoJSONData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.collections.ArrayList

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

         /*   var a=ArrayList<String>()
            for(i in 0 until parsedResponse.size) {
                a.add("CODENAME"+parsedResponse.get(i).codename+"\n")
                a.add("ABSOLUTE MAG"+parsedResponse.get(i).absoluteMagnitude.toString()+"\n")
                a.add("ID"+parsedResponse.get(i).id.toString()+"\n")
            }*/

            _response.postValue(parsedResponse.toString())
        }
    }
}

fun parseAsteroidsJsonResult(jsonResult: JSONObject): ArrayList<NeoJSONData> {
    val nearEarthObjectsJson = jsonResult.getJSONObject("near_earth_objects")
    val asteroidList = ArrayList<NeoJSONData>()
            val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray("2015-09-08")
        for(i in 0 until dateAsteroidJsonArray.length()) {
            val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
            val id = asteroidJson.getLong("id")
            val codename = asteroidJson.getString("name")
            val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
            val asteroid = NeoJSONData(id, codename, absoluteMagnitude)
            asteroidList.add(asteroid)
        }
    return asteroidList
}

