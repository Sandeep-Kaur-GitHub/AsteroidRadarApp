package com.example.asteroidradar

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.asteroidradar.databinding.ActivityDetailBinding

class detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.imageButton.setOnClickListener(View.OnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage(R.string.setMessage)
                // if the dialog is cancelable
                .setCancelable(false)
                .setNegativeButton("ACEPTAR", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("AlertDialogExample")
            // show alert dialog
            alert.show()
        })

        val intent = intent
        val max_dia = intent.getStringExtra("maxDia")
        binding.maxDiaTextView.text=max_dia + " Km"

        val keyDate=intent.getStringExtra("keyOfDate")
        binding.keyDateTextView.text=keyDate

        val  abs_magnitude=intent.getDoubleExtra("abs_magnitude",0.00)
        binding.absMagnitudeTextView.text= abs_magnitude.toString()+ " au"

        val relative_velocity=intent.getStringExtra("relative_velocity")
        binding.relVelocityTextView.text=relative_velocity

        val distance_from_earth=intent.getStringExtra("distance_from_earth")
        binding.earthDistanceTextView.text=distance_from_earth

        val isHazardous= intent.getBooleanExtra("isDangerous",false)
         if (isHazardous)
         {
             binding.imageDangerous.setImageResource(R.drawable.hazardous)
         }
        else
         {
             binding.imageDangerous.setImageResource(R.drawable.not_hazardous)
         }


    }
}