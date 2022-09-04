package com.example.asteroidradar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.network.NeoJSONData


class NeoDataAdapter:RecyclerView.Adapter<NeoDataAdapter.ViewHolder>()
{
    var data= listOf<NeoJSONData>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.date_text_view.text=item.keyOfDate
        holder.code_text_view.text=item.codename
        if (item.is_potentially_hazardous_asteroid)
        {
            holder.image.setImageResource(R.drawable.sadface)
        }else
        {
            holder.image.setImageResource(R.drawable.smilingface)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.custom_list_layout,parent,false)
        return ViewHolder(view)

    }
    //class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val code_text_view:TextView=itemView.findViewById(R.id.codename_text_view)
        val date_text_view:TextView= itemView.findViewById(R.id.date_text_view)
        val image:ImageView=itemView.findViewById(R.id.emoji_imageView)

    }

}