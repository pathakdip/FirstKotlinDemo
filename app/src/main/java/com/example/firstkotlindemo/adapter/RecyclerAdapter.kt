package com.example.firstkotlindemo.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.model.EmpModelClass
import com.example.firstkotlindemo.model.UserData

//class RecyclerAdapter(private val context: Activity, private val name: Array<String>, val userList: ArrayList<EmpModelClass>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {
class RecyclerAdapter(private val context: Activity, private val name: Array<String>, private val email: Array<String>, val userList: List<EmpModelClass>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    override fun getItemCount(): Int {
        Log.e("RecyclerAdapter","userList size: "+userList.size)
        return userList.size

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.display_data_row, parent, false)
        return ViewHolder(inflatedView)
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: EmpModelClass) {
            val textViewName = itemView.findViewById(R.id.itemName) as TextView
            val textViewAddress  = itemView.findViewById(R.id.itemAddress) as TextView
            textViewName.text = "Name: "+user.userName
            textViewAddress.text = "Email: "+user.userEmail

            Log.e("RecyclerAdapter","name: "+user.userName)
            Log.e("RecyclerAdapter","address: "+user.userEmail)
        }
    }
}
