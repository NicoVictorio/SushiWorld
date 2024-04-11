package com.ubaya.sushiworld.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.sushiworld.model.User
import org.json.JSONObject

class UserViewModel (application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User?>()
    val statusLD = MutableLiveData<String>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(username:String, password:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/login?usernameLogin=$username&passwordLogin=$password"

        var stringRequest = StringRequest(
            Request.Method.GET,url,
            {
                Log.d("showvoley", it.toString())
                var obj = JSONObject(it) //ada status sm data
                var resultDb = obj.getString("status")
                if (resultDb == "OK") {
                    var data = obj.getJSONObject("data") //Ini ganti jdi GSON
                    val sType = object : TypeToken<User>() { }.type
                    val result = Gson().fromJson<User>(data.toString(), sType)
                    userLD.value = result as User?
                    statusLD.value = resultDb
                }
                else{
                    userLD.value = null
                    statusLD.value = resultDb
//                    Log.d("showvolei1", userLD.value.toString())
                }
            },
            {
//                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun signup(
        username:String, nama_depan:String, nama_belakang:String,
        email:String, password:String, photo_url:String
    ){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/signup?usernameLogin=$username&passwordLogin=$password&firstname=$nama_depan&lastname=$nama_belakang&email=$email&photourl=$photo_url"
        var stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<String>() { }.type
                val result = Gson().fromJson<String>(it, sType)
                statusLD.value = result
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}