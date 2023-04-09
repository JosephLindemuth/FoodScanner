package com.example.foodscanner

import android.app.Application

class FoodScanner : Application() {
    public val scanHistory = HashSet<String>()

    override fun onCreate() {
        super.onCreate()
    }
}