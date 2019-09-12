package com.example.firstkotlindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.example.firstkotlindemo.utils.DatabaseHandler
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.model.EmpModelClass
import com.example.firstkotlindemo.model.User
import com.example.firstkotlindemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity

class SignUpActivity : AppCompatActivity() {
    private val activity = this@SignUpActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val user= User()
        val userViewModel= UserViewModel(user)

        val binding= DataBindingUtil.setContentView<ViewDataBinding>(this,R.layout.activity_sign_up)
        binding.setVariable(BR.registerModel,userViewModel)


        btnSignUp.setOnClickListener { view ->
            // Handler code here.
            Toast.makeText(activity,"Sign-up", Toast.LENGTH_LONG).show()
            Log.e("SignUpActivity","Sign-up button clicked")
            saveRecord(view)

            startActivity<MainActivity>()
        }

        txtLogin.setOnClickListener {
            // Handler code here.
            Log.e("SignUpActivity"," Login Link  clicked")
            //                val intent = Intent(applicationContext, MainActivity::class.java)
            //                startActivity(intent)

            startActivity<MainActivity>()
        }
    }

    //method for saving records in database
    fun saveRecord(view: View){

        val name = ed_Name.text.toString()
        val email = ed_Email.text.toString()
        val contact= ed_Contact.text.toString()
        val password= ed_Password.text.toString()

        Log.e("SignUpActivity","name: "+name)
        Log.e("SignUpActivity","email: "+email)
        Log.e("SignUpActivity","contact: "+contact)
        Log.e("SignUpActivity","password: "+password)


        val databaseHandler = DatabaseHandler(view.context)
        if(name.trim()!="" && email.trim()!=""){
            val status = databaseHandler.addEmployee(EmpModelClass(email, password,name,contact))
            if(status > -1){
                Log.e("SignUpActivity","record saved")
                Toast.makeText(view.context,"record saved", Toast.LENGTH_LONG).show()
            }
        }
        else{
            Log.e("SignUpActivity","name or email cannot be blank")
            Toast.makeText(view.context,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
        }
    }
}
