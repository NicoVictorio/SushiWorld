package com.ubaya.sushiworld.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubaya.sushiworld.R
import com.ubaya.sushiworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}