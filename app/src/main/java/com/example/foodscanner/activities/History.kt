package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.example.foodscanner.ui.HistoryRecyclerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val scanHistory: Array<String> = (application as FoodScanner).scanHistory.toTypedArray();

        scanHistory.forEachIndexed { index, barcode ->
            Log.d("History Value $index", barcode)
        }

        // set up RecyclerView for files
        val scanHistoryList: RecyclerView = findViewById<RecyclerView>(R.id.ScanHistory)
        scanHistoryList.layoutManager = LinearLayoutManager(this)
        scanHistoryList.setHasFixedSize(true)
        val historyAdapter: HistoryRecyclerAdapter = HistoryRecyclerAdapter(scanHistory)
        scanHistoryList.adapter = historyAdapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Scan -> {
                    val outIntent: Intent = Intent(applicationContext, Scan::class.java)
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
                R.id.History -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }
}