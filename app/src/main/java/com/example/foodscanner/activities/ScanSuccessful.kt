package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScanSuccessful : AppCompatActivity() {

    var lastScan: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_successful)

        // get data from scanHistory
        lastScan = (application as FoodScanner).lastScanned

        // set scannedItem textView to display what the last scanned item was
        val textView = findViewById<View>(R.id.scannedItem) as TextView
        textView.text = lastScan

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.History -> {
                    val outIntent: Intent = Intent(applicationContext, History::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> {
                    val outIntent: Intent = Intent(applicationContext, Favorites::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Scan -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }

    fun getMyData(): Bundle? {
        val bundle = Bundle()
        bundle.putString("lastScan", lastScan)
        return bundle
    }
}