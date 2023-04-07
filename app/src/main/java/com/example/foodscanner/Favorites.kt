package com.example.foodscanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Favorites : AppCompatActivity() {
    //var bottomNavigationView: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.History -> {
                    val outIntent: Intent = Intent(applicationContext, History::class.java)
                    outIntent.putExtra("ScannedCodes", intent.getStringArrayExtra("ScannedCodes"))
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Scan -> {
                    val outIntent: Intent = Intent(applicationContext, Scan::class.java)
                    outIntent.putExtra("ScannedCodes", intent.getStringArrayExtra("ScannedCodes"))
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }
}