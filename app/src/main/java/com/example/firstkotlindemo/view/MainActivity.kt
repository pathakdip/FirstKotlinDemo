package com.example.firstkotlindemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import com.example.firstkotlindemo.DatabaseHandler
import com.example.firstkotlindemo.R
import com.example.firstkotlindemo.databinding.ActivityMainBinding
import com.example.firstkotlindemo.model.User
import com.example.firstkotlindemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private val activity = this@MainActivity

    lateinit var databaseHandler : DatabaseHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user= User()
        val userViewModel=UserViewModel(user)

        databaseHandler=DatabaseHandler (this)

        //data binding layout
        val binding:ActivityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.setVariable(BR.loginModel,userViewModel)

        //login button action
        btnLogin.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                Toast.makeText(activity,"Login",Toast.LENGTH_LONG).show()
                Log.e("MainActivity","Login button clicked")

                checkUser(view)
                //startActivity(Intent(this@MainActivity,HomeActivity::class.java))
                //val intent = Intent(this@MainActivity,HomeActivity::class.java);
                //val userName = ed_Login_Email.text.toString()
                //val password = ed_Login_Password.text.toString()
                /*intent.putExtra("Username", userName)
                intent.putExtra("Password", password)
                startActivity(intent)*/

                //startActivity<HomeActivity>("Username" to userName,"Password" to password)
            }
        })

        //sign up form link
        txtSignUp.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.
                Log.e("MainActivity","Sign up link clicked")

                val intent = Intent(activity, SignUpActivity::class.java)
                startActivity(intent)

            }
        })
    }

    fun checkUser(view: View)
    {
        val email = ed_Login_Email.text.toString()
        val password= ed_Login_Password.text.toString()

        Log.e("MainActivity","email: "+email)
        Log.e("MainActivity","password: "+password)

        val databaseHandler = DatabaseHandler(view.context)
        if(email.trim()!="" && password.trim()!=""){
            val status = databaseHandler.checkUser(ed_Login_Email!!.text.toString().trim { it <= ' ' }, ed_Login_Password!!.text.toString().trim { it <= ' ' })
            if(status){
                Log.e("MainActivity","Login Successful")
                Toast.makeText(view.context,"Login Successful", Toast.LENGTH_LONG).show()
                startActivity<HomeActivity>("Username" to email)

            }
        }
        else{
            Log.e("MainActivity","Enter valid credentials")
            Toast.makeText(view.context,"Enter valid credentials", Toast.LENGTH_LONG).show()
        }

    }

}
