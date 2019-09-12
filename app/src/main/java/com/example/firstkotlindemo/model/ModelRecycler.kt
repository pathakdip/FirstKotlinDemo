package com.example.firstkotlindemo.model

import android.R.attr.name



class ModelRecycler {

    private var name: String? = null
    private var country: String? = null
    private var city: String? = null
    private var imgURL: String? = null

    fun getImgURL(): String? {
        return imgURL
    }

    fun setImgURL(imgURL: String) {
        this.imgURL = imgURL
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String) {
        this.country = country
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}
