package com.example.foodscanner.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.R
import com.example.foodscanner.ui.main.AllergiesFragment.Companion.newInstance

class allergies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergies)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, newInstance())
                .commitNow()
        }


    }
}