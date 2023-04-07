package com.example.foodscanner.ui

class HistoryItem() {
    var upc: String = ""
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