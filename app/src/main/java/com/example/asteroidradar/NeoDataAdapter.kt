package com.example.asteroidradar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        holder.id_text_view.text=item.id.toString()
        holder.code_text_view.text=item.codename
        Log.i("gaaa",""+item.codename)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.custom_list_layout,parent,false)
        return ViewHolder(view)

    }
    //class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val id_text_view:TextView= itemView.findViewById(R.id.id_text_view)
        val code_text_view:TextView=itemView.findViewById(R.id.codename_text_view)

    }

}