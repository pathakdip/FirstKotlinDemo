package com.example.firstkotlindemo.model

import java.util.*

class User:Observable()
{
    var id: String ?= null
    set(value)
    {
        field=value
        setChangedandNotify("id")
    }



    var name: String = ""
        set(value)
        {
            field=value
            setChangedandNotify("name")
        }

    var email: String = ""
        set(value)
        {
            field=value
            setChangedandNotify("email")
        }
    var password: String = ""
        set(value)
        {
            field=value
            setChangedandNotify("password")
        }
    var contact: String = ""
        set(value)
        {
            field=value
            setChangedandNotify("contact")
        }

    private fun setChangedandNotify(s: Any) {
        setChanged()
        notifyObservers(s)
    }

    //for registration
    /*constructor(name:String,email:String,password:String,contact:String):this()
    {
        this.name=name
        this.email=email
        this.password=password
        this.contact=contact
    }*/

    //for login
    /*constructor(email:String,password:String):this()
    {
        this.email=email
        this.password=password
    }*/



}