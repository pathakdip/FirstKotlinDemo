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
}