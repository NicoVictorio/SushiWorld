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

class SushiDetailViewModel(application: Application): AndroidViewModel(application) {
    val sushiLD = MutableLiveData<Sushi>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(sushiId: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://icfubaya2023.com/detailsushi?id="+sushiId

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object : TypeToken<Sushi>() { }.type
                sushiLD.value = Gson().fromJson<Sushi>(it, sType)
                Log.d("showvoley", sushiLD.value.toString())
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