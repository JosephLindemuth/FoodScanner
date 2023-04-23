package com.example.foodscanner.ui

import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.R

class FavoritesItem() {
    var upc: String = ""

    var fav: Boolean = false
    var lastScanned: String = ""



    constructor(upc: String) : this()
    {
        this.upc = upc
    }

    constructor(upc: String, scanned: String) : this(upc)
    {
        this.lastScanned = scanned
    }
}