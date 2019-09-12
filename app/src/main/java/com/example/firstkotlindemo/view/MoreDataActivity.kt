package com.example.firstkotlindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.adapter.RetrofitAdapter
import com.example.firstkotlindemo.interfaces.RecyclerInterface
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONException
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import com.example.firstkotlindemo.model.ModelRecycler

class MoreDataActivity : AppCompatActivity() {

    private val activity = this@MoreDataActivity
    private var retrofitAdapter: RetrofitAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_data)
        recyclerView = findViewById(R.id.recycler)

        fetchJSON()

    }

    private fun fetchJSON() {

        val retrofit = Retrofit.Builder()
            .baseUrl(RecyclerInterface.JSONURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api = retrofit.create(RecyclerInterface::class.java)

        val call = api.string

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("Responsestring", response.body().toString())
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString())

                        val jsonresponse = response.body().toString()
                        writeRecycler(jsonresponse)

                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }
        })
    }

    private fun writeRecycler(response: String) {

        try {
            //getting the whole json object from the response
            val obj = JSONObject(response)
            if (obj.optString("status") == "true") {

                val modelRecyclerArrayList = ArrayList<ModelRecycler>()
                val dataArray = obj.getJSONArray("data")

                for (i in 0 until dataArray.length()) {

                    val modelRecycler = ModelRecycler()
                    val dataobj = dataArray.getJSONObject(i)

                    modelRecycler.setImgURL(dataobj.getString("imgURL"))
                    modelRecycler.setName(dataobj.getString("name"))
                    modelRecycler.setCountry(dataobj.getString("country"))
                    modelRecycler.setCity(dataobj.getString("city"))

                    modelRecyclerArrayList.add(modelRecycler)

                }

                retrofitAdapter = RetrofitAdapter(this, modelRecyclerArrayList)
                recyclerView!!.setAdapter(retrofitAdapter)
                recyclerView!!.setLayoutManager(
                    LinearLayoutManager(
                        applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                )

            } else {
                Toast.makeText(activity, obj.optString("message") + "", Toast.LENGTH_SHORT)
                    .show()
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }


}
