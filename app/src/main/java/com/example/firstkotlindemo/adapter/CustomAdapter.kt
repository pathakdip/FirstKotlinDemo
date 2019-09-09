package com.example.firstkotlindemo.adapter

import android.Manifest
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.model.ContactModel
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import android.Manifest.permission
import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat




class CustomAdapter(private val context: Context, private val contactModelArrayList: ArrayList<ContactModel>) : BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return contactModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return contactModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.contact_listview_item, null, true)

            holder.tvname = convertView!!.findViewById(R.id.name) as TextView
            holder.tvnumber = convertView.findViewById(R.id.number) as TextView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvname?.setText(contactModelArrayList[position].getNames())
        holder.tvnumber?.setText(contactModelArrayList[position].getNumbers())



        holder.tvname?.setOnClickListener {
            Toast.makeText(context,contactModelArrayList[position].getNames(),Toast.LENGTH_LONG).show()

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${contactModelArrayList[position].getNumbers()}"))

            if (ContextCompat.checkSelfPermission(context, CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {

                ActivityCompat.requestPermissions(context as Activity, arrayOf(CALL_PHONE),1)

                // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            } else {
                //You already have permission
                try {
                    context.startActivity(intent)
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }

            }
            //context.startActivity(intent)
        }

        return convertView
    }

    private inner class ViewHolder {

        var tvname: TextView? = null
        var tvnumber: TextView? = null

    }
}
