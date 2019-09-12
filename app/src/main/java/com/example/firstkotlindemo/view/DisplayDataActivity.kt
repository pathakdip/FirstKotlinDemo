package com.example.firstkotlindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.adapter.RecyclerAdapter
import com.example.firstkotlindemo.model.EmpModelClass
import com.example.firstkotlindemo.model.UserData
import com.example.firstkotlindemo.utils.DatabaseHandler
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity

class DisplayDataActivity : AppCompatActivity() {

    private val activity = this@DisplayDataActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //val users = ArrayList<UserData>()
        val users = ArrayList<EmpModelClass>()

        //adding some dummy data to the list
        /*users.add(UserData("Belal Khan", "Ranchi Jharkhand"))
        users.add(UserData("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(UserData("Faiz Khan", "Ranchi Jharkhand"))
        users.add(UserData("Yashar Khan", "Ranchi Jharkhand"))*/

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail

            Log.e("DisplayDataActivity","empName: "+e.userName)
            Log.e("DisplayDataActivity","empEmail: "+e.userEmail)

            index++
        }
        Log.e("DisplayDataActivity","emp size: "+emp.size)

        val adapter = RecyclerAdapter(this,empArrayName,empArrayEmail,emp)
        recyclerView.adapter = adapter

        //setting adapter for dummy data
        //val adapter = RecyclerAdapter(users)
        //recyclerView.adapter = adapter

        /*view more data*/
        btnMoreData.setOnClickListener {
            // Handler code here.
            Log.e("DisplayDataActivity","more data button clicked")

            startActivity<MoreDataActivity>()
        }

        /*logout*/
        btnLogout.setOnClickListener {
            // Handler code here.
            Log.e("MoreDataActivity","logout button clicked")

            startActivity<MainActivity>()
            finish()
        }
    }
}
