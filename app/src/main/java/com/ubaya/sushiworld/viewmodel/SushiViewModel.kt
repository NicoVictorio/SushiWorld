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
import com.ubaya.sushiworld.model.Sushi

class SushiViewModel (application: Application): AndroidViewModel(application) {
    val sushiLD = MutableLiveData<ArrayList<Sushi>>()
    val sushiLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        sushiLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/sushi"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<List<Sushi>>() { }.type
                val result = Gson().fromJson<List<Sushi>>(it, sType)
                sushiLD.value = result as ArrayList<Sushi>?
                loadingLD.value = false
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                sushiLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}