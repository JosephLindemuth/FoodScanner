package com.example.foodscanner

import android.R
import android.app.Application
import android.view.Menu
import android.view.MenuInflater


class FoodScanner : Application() {
    public var myAllergies = ArrayList<Int>()
    public val favScans = HashSet<String>()
    public val scanHistory = HashSet<String>() // Hashset is unordered which should be change to be an ordered list
    public var lastScanned: String = ""

    override fun onCreate() {
        super.onCreate()
    }

}