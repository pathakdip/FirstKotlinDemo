package com.example.firstkotlindemo.interfaces

import retrofit2.Call
import retrofit2.http.GET


interface RecyclerInterface {

    @get:GET("json_parsing.php")
    val string: Call<String>

    companion object {

        val JSONURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/"
    }
}
