package com.example.foodscanner

import android.app.Application

class FoodScanner : Application() {
    public val scanHistory = HashSet<String>() // Hashset is unordered which should be change to be an ordered list
    public var lastScanned: String = ""

    override fun onCreate() {
        super.onCreate()
    }
}