package com.example.asteroidradar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.network.NeoAPI
import com.example.asteroidradar.network.NeoJSONData
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
        NeoAPI.retrofitService.getProperties().enqueue(object : Callback<List<NeoJSONData>> {
            override fun onResponse(call: Call<List<NeoJSONData>>, response: Response<List<NeoJSONData>>) {
                _response.value = response.toString()
            }
            override fun onFailure(call: Call<List<NeoJSONData>>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

        })
    }
}