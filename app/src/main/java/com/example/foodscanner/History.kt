package com.example.foodscanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class History : AppCompatActivity() {
    //var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Scan -> {
                    startActivity(Intent(applicationContext, Scan::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> {
                    startActivity(Intent(applicationContext, Favorites::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.History -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }
}