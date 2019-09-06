package com.example.firstkotlindemo.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import com.example.firstkotlindemo.DatabaseHandler
import com.example.firstkotlindemo.model.EmpModelClass
import com.example.firstkotlindemo.model.User
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class UserViewModel(val user: User) : Observer,BaseObservable(){

    init {
        user.addObserver(this)
    }

    val userName: String
    @Bindable get()
    {
        return user.name
    }

    val userEmail:String
    @Bindable get()
    {
        return user.email
    }

    val password:String
    @Bindable get()
    {
        return user.password
    }

    val contact:String
    @Bindable get()
    {
        return user.contact
    }

    override fun update(o: Observable?, arg: Any?) {

        if(arg==String)
        {
            if(arg=="email")
            {
                notifyPropertyChanged(BR.userEmail)
            }
            else if(arg=="name")
            {
                notifyPropertyChanged(BR.userName)
            }
            else if(arg=="password")
            {
                notifyPropertyChanged(BR.password)
            }
            else if(arg=="contact")
            {
                notifyPropertyChanged(BR.contact)
            }
        }
    }

    //method for saving records in database
    fun saveRecord(view: View){

        val name = userName
        val email = userEmail
        val contact= contact
        val password= password

        Log.e("UserViewModel","name: "+name)
        Log.e("UserViewModel","email: "+email)
        Log.e("UserViewModel","contact: "+contact)
        Log.e("UserViewModel","password: "+password)

        val databaseHandler = DatabaseHandler(view.context)
        if(name.trim()!="" && email.trim()!=""){
            val status = databaseHandler.addEmployee(EmpModelClass(email, password,name,contact))
            if(status > -1){
                Log.e("UserViewModel","record saved")
                Toast.makeText(view.context,"record saved", Toast.LENGTH_LONG).show()

            }
        }
        else{
            Log.e("UserViewModel","name or email cannot be blank")
            Toast.makeText(view.context,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
        }

    }

}
