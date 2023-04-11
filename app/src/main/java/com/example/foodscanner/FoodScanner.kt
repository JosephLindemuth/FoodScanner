package com.example.foodscanner

import android.R
import android.app.Application
import android.view.Menu
import android.view.MenuInflater


class FoodScanner : Application() {
    public val scanHistory = HashSet<String>()

    override fun onCreate() {
        super.onCreate()
    }

}