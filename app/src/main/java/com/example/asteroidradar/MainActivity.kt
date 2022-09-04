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

class MainActivity : AppCompatActivity(),NeoDataAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val adapter = NeoDataAdapter(this)
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
        startActivity(intent)
        Toast.makeText(this,"helloo $position is clicked",Toast.LENGTH_LONG).show()
    }

}