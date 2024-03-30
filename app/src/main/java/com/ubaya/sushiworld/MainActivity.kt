package com.ubaya.sushiworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubaya.sushiworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        //menyuruh nav controller buat handle navview
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.bottomNav.setupWithNavController(navController)

    }
}