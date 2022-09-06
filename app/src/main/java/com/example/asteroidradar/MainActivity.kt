package com.example.asteroidradar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.asteroidradar.databinding.ActivityMainBinding
import com.example.asteroidradar.network.NeoAPI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NeoDataAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    val adapter = NeoDataAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = adapter

        viewModel.allData.observe(this, Observer {
            it.let {
                adapter.data = it
            }
        })
        viewModel.resImg.observe(this, Observer {

            Picasso.with(this)
                .load(it.url)
                .into(binding.imageView)
        })

        super.onCreate(savedInstanceState)
    }

    override fun onItemClick(position: Int) {
        intent = Intent(applicationContext, detail::class.java)
        viewModel.allData.observe(this, Observer {
            it.let {

                val maxDia = adapter.data.get(position).max_diameter
                intent.putExtra("maxDia", maxDia.toString())

                val isDangerous = adapter.data.get(position).is_potentially_hazardous_asteroid
                intent.putExtra("isDangerous", isDangerous)

                val keyDate = adapter.data.get(position).keyOfDate
                intent.putExtra("keyOfDate", keyDate)

                val abs_magnitude = adapter.data.get(position).absoluteMagnitude
                intent.putExtra("abs_magnitude", abs_magnitude)

                val relative_velocity = adapter.data.get(position).relative_velocity
                intent.putExtra("relative_velocity", relative_velocity)

                val distance_from_earth = adapter.data.get(position).distance_from_earth
                intent.putExtra("distance_from_earth", distance_from_earth)

                startActivity(intent)


            }
        })
    }
}