package com.ubaya.sushiworld.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.sushiworld.databinding.ActivityLoginBinding
import com.ubaya.sushiworld.viewmodel.UserViewModel
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences

    var userId : Int = 0
    var username : String = ""
    var nama_depan : String = ""
    var nama_belakang : String = ""
    var email : String = ""
    var password : String = ""
    var photo_url : String = ""

    companion object{
        val USERID = "USERID"
        val USERNAME = "USERNAME"
        val NAMADEPAN = "NAMADEPAN"
        val NAMABELAKANG = "NAMABELAKANG"
        val EMAIL = "EMAIL"
        val PASSWORD = "PASSWORD"
        val PHOTOURL = "PHOTOURL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedFile = "com.ubaya.sushiworld"
        shared = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var checkLogin = shared.getInt("USERID", -1)
        if (checkLogin != -1) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnLogIn.setOnClickListener {
            username = binding.txtUsername.text.toString()
            password = binding.txtPasswordLogin.text.toString()
            if (username != "" && password != "") {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Username dan Password cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        var sharedFile = "com.ubaya.sushiworld"
        shared = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()

        viewModel.userLD.observe(this, Observer { user ->
            Log.d("showvolei123213", user.toString())
            if (user != null) {
                userId = user.id?.toInt() ?: -1
                username = user.username.toString()
                nama_depan = user.nama_depan.toString()
                nama_belakang = user.nama_belakang.toString()
                email = user.email.toString()
                password = user.password.toString()
                photo_url = user.photo_url.toString()

                editor.putInt(USERID, userId)
                editor.putString(USERNAME, username)
                editor.putString(NAMADEPAN, nama_depan)
                editor.putString(NAMABELAKANG, nama_belakang)
                editor.putString(EMAIL, email)
                editor.putString(PASSWORD, password)
                editor.putString(PHOTOURL, photo_url)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(application, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        })
    }
}