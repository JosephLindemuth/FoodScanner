package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.example.foodscanner.ui.HistoryRecyclerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

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
        val historyAdapter = HistoryRecyclerAdapter(scanHistory)
        scanHistoryList.adapter = historyAdapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId

        //NavigationBarView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener)

        bottomNavigationView.setOnItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_topright, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.AllergiesMenu -> {
                val outIntent: Intent = Intent(applicationContext, allergies::class.java)
                startActivity(outIntent)
                overridePendingTransition(0, 0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}