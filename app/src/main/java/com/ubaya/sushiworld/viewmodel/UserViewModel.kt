package com.ubaya.sushiworld.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.sushiworld.model.User

class UserViewModel (application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
//    var statusLD = MutableLiveData<String>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username:String, password:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/login?usernameLogin=$username&passwordLogin=$password"

        var stringRequest = StringRequest(
            Request.Method.GET,url,
            Response.Listener{
                Log.d("showvoley", it.toString())
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(it, sType)
                userLD.value = result as User?
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

//    fun signup(
//        username:String, nama_depan:String, nama_belakang:String,
//        email:String, password:String, photo_url:String
//    ){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://icfubaya2023.com/signup?usernameLogin=$username&passwordLogin=$password&firstname=$nama_depan&lastname=$nama_belakang&email=$email&photourl=$photo_url"
//
//        var stringRequest = StringRequest(
//            Request.Method.GET, url,
//            Response.Listener {
//                Log.d("showvoley", it.toString())
//                val sType = object : TypeToken<String>() { }.type
//                val result = Gson().fromJson<String>(it, sType)
//                val status = if (result == "OK") "OK" else "FAILED"
//                statusLD.value = status
//            },
//            Response.ErrorListener { error ->
//                Log.e(TAG, error.message ?: "Error")
//            })
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}