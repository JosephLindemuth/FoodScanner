package com.example.foodscanner.ui

import android.widget.Button

class HistoryItem() {
    var upc: String = ""
    var btn: Boolean = false
    var lastScanned: String = ""

    constructor(upc: String) : this()
    {
        this.upc = upc
        this.btn = btn
    }

    constructor(upc: String, scanned: String) : this(upc)
    {
        this.lastScanned = scanned
    }
}