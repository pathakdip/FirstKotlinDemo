package com.example.firstkotlindemo.adapter

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.model.ModelRecycler

class RetrofitAdapter(ctx: Context, private val dataModelArrayList: ArrayList<ModelRecycler>) : RecyclerView.Adapter<RetrofitAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitAdapter.MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.more_data_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RetrofitAdapter.MyViewHolder, position: Int) {

        Picasso.get().load(dataModelArrayList[position].getImgURL()).into(holder.iv)
        holder.name.text = dataModelArrayList[position].getName()
        holder.country.text = dataModelArrayList[position].getCountry()
        holder.city.text = dataModelArrayList[position].getCity()
    }

    override fun getItemCount(): Int {
        return dataModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var country: TextView
        var name: TextView
        var city: TextView
        var iv: ImageView

        init {
            country = itemView.findViewById(R.id.country) as TextView
            name = itemView.findViewById(R.id.name) as TextView
            city = itemView.findViewById(R.id.city) as TextView
            iv = itemView.findViewById(R.id.iv) as ImageView
        }

    }
}
