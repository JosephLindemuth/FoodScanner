package com.example.foodscanner.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.foodscanner.FoodScanner
import com.example.foodscanner.R
import com.example.foodscanner.databinding.FragmentOverviewBinding
import com.example.foodscanner.network.ProductInfo
import com.example.foodscanner.ui.overview.OverviewViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ScanSuccessful : AppCompatActivity() {

    private val viewModel: OverviewViewModel by viewModels()

    var lastScan: String = ""
    private lateinit var app: FoodScanner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_successful)

        app = application as FoodScanner

        // get data from scanHistory
        lastScan = (application as FoodScanner).lastScanned

        // set scannedItem textView to display what the last scanned item was
        val textView = findViewById<View>(R.id.scannedItem) as TextView
        textView.text = lastScan



        val fav : Button = findViewById<Button>(R.id.btn)
        fav.setOnClickListener{
            if(app.favScans.contains(lastScan)){
                app.favScans.remove(lastScan)
            }
            else{
                app.favScans.add(lastScan)
            }

        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigator)
        bottomNavigationView.selectedItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.History -> {
                    val outIntent: Intent = Intent(applicationContext, History::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(1, 1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.fav -> {
                    val outIntent: Intent = Intent(applicationContext, Favorites::class.java)
                    startActivity(outIntent)
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.Scan -> {
                    val outIntent: Intent = Intent(applicationContext, Scan::class.java)
                    startActivity(outIntent)

                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    fun getMyData(): Bundle? {
        val bundle = Bundle()
        bundle.putString("lastScan", lastScan)
        bundle.putIntegerArrayList("myAllergies", app.myAllergies)
        return bundle
    }
}