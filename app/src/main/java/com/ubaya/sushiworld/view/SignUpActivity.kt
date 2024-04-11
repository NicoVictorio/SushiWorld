package com.ubaya.sushiworld.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.sushiworld.R
import com.ubaya.sushiworld.databinding.ActivityLoginBinding
import com.ubaya.sushiworld.databinding.ActivitySignUpBinding
import com.ubaya.sushiworld.viewmodel.UserViewModel
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: UserViewModel

    var userId : Int = 0
    var username : String = ""
    var nama_depan : String = ""
    var nama_belakang : String = ""
    var email : String = ""
    var password : String = ""
    var repassword : String = ""
    var photo_url : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp2.setOnClickListener {
            username = binding.txtUsername.text.toString()
            nama_depan = binding.txtFirstName.text.toString()
            nama_belakang = binding.txtLastName.text.toString()
            email = binding.txtEmail.text.toString()
            password = binding.txtPassword.text.toString()
            repassword = binding.txtRePassword.text.toString()
            photo_url = binding.txtPhotoUrl.text.toString()

            if (password == repassword) {
                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.signup(username, nama_depan, nama_belakang, email, password, photo_url)
                viewModel.statusLD.observe(this, Observer {
                    if(it.toString() == "OK"){
                        Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                    else{
                        Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, SignUpActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                })
            } else {
                val builder = AlertDialog.Builder(it.context)
                builder.setMessage("Password and Re-type Password not match!")
                builder.setPositiveButton("OK", null)
                builder.create().show()
            }
        }
    }
}